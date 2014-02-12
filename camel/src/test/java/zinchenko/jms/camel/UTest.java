package zinchenko.jms.camel;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

import javax.jms.ConnectionFactory;

/**
 * User: zinchenko
 * Date: 12.02.14
 */
public class UTest {


    @Test
    public void test() throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("test-jms:queue:fq")
                        .bean(Bean.class);
/*                  .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                System.out.println(exchange.getIn().getBody());
                            }
                        });*/

                //.to("test-jms:queue:sq");
                //.to("file:test");
            }
        });
        ConnectionFactory connectionFactory = new
                ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
        camelContext.addComponent("test-jms",
                JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        ProducerTemplate template = camelContext.createProducerTemplate();
        camelContext.start();
//        for (int i = 0; i < 10; i++) {
//            template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
//        }
        Thread.sleep(1000);
        camelContext.stop();
    }

}
