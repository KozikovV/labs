package zinchenko.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Before;
import org.junit.Test;
import zinchenko.filecopy.impl.TransformProcessor;

import javax.jms.ConnectionFactory;

import static org.apache.camel.component.jms.JmsComponent.jmsComponentClientAcknowledge;

/**
 * User: zinchenko
 * Date: 09.02.14
 */
public class ByCamelTest {



    @Before
    public void before(){

    }

    @Test
    public void test(){
        try {
            CamelContext camelContext = new DefaultCamelContext();
            ConnectionFactory connectionFactory =
                    new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
                    //new ActiveMQConnectionFactory("tcp://192.168.1.150:61616");
            camelContext.addComponent("jms", ActiveMQComponent
                    .jmsComponentAutoAcknowledge(connectionFactory));

            camelContext.createProducerTemplate().sendBody("jms:q", "m123");
            camelContext.start();
            Object o = camelContext.createProducerTemplate().requestBody("jms:q", "");
            Thread.sleep(1000);
            camelContext.stop();
        } catch (Exception e) {
            throw new RuntimeException("Failed file copy ", e);
        }
    }

}
