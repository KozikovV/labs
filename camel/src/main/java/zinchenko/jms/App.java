package zinchenko.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import java.util.concurrent.TimeUnit;

public class App {
    private static BrokerService broker;

    public static void main(String[] args) throws Exception {
        try {
            startBroker();


            CamelContext ctx = createCamelContext();
            ctx.start();
            ctx.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    /* Our direct route will take a message, and set the message to group 1 if the body is an integer,
                     * otherwise set the group to 2.
                     *
                     * This demonstrates the following concepts:
                     *  1) Header Manipulation
                     *  2) Checking the payload type of the body and using it in a choice.
                     *  3) JMS Message groups
                     */

                    from("direct:begin")
                            .choice()
                            .when(body().isInstanceOf(Integer.class)).setHeader("JMSXGroupID",constant("1"))
                            .otherwise().setHeader("JMSXGroupID",constant("2"))
                            .end()
                            .to("amq:queue:Message.Group.Test");

                    /* These two are competing consumers */
                    from("amq:queue:Message.Group.Test").routeId("Route A").log("Received: ${body}");
                    from("amq:queue:Message.Group.Test").routeId("Route B").log("Received: ${body}");
                }
            });

            sendMessages(ctx.createProducerTemplate());
            Thread.sleep(TimeUnit.SECONDS.toMillis(10));
            stopBroker();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static CamelContext createCamelContext() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("vm://localhost/");
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setMaxConnections(8);
        pooledConnectionFactory.setMaximumActiveSessionPerConnection(500);

        ActiveMQComponent activeMQComponent = ActiveMQComponent.activeMQComponent();
        activeMQComponent.setUsePooledConnection(true);
        activeMQComponent.setConnectionFactory(pooledConnectionFactory);
        camelContext.addComponent("amq", activeMQComponent);

        return camelContext;
    }

    private static void sendMessages(ProducerTemplate pt) throws Exception {
        for (int i = 0; i < 10; i++) {
            pt.sendBody("direct:begin", Integer.valueOf(i));
        }

        for (int i = 0; i < 10; i++) {
            pt.sendBody("direct:begin", "next group");
        }

        pt.sendBody("direct:begin", Integer.valueOf(1));
        pt.sendBody("direct:begin", "foo");
        pt.sendBody("direct:begin", Integer.valueOf(2));
    }

    private static void startBroker() throws Exception {
        broker = new BrokerService();
        broker.addConnector("vm://localhost");
        broker.start();
    }

    private static void stopBroker() throws Exception {
        broker.stop();
    }
}
