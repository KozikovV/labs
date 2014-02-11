package zinchenko.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.xbean.BrokerFactoryBean;
import org.apache.camel.CamelContext;
import org.apache.camel.ExchangePattern;
import org.apache.camel.ProducerTemplate;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("sendReceiveTest.xml")
public class SenderReceiverTest {

//    @Autowired
//    ApplicationContext applicationContext;

//    @Autowired
//    @Qualifier(value = "camel")
//    CamelContext springCamelContext;

//    @Autowired
//    ProducerTemplate producerTemplate;

    Sender sender;

    Receiver receiver;

    BrokerService broker;

    @Before
    public void setUp() throws Exception {
        sender = new Sender();
        receiver = new Receiver();

//        broker = new BrokerService();
//        broker.addConnector("tcp://localhost:61616");
//        broker.start();

    }

    @After
    public void tearDown() throws Exception {
//        broker.stop();
    }

    @Test
    public void testSendMessage() throws Exception {
        String expectedMessage = "m";
        sender.sendMessage(expectedMessage);

//        String result = receiver.receiveMessage();
//        ProducerTemplate producerTemplate = springCamelContext.createProducerTemplate();
//        String result = (String) producerTemplate.requestBody("jms:queue:SAMPLEQUEUE");
//        Assert.assertEquals(expectedMessage, result);
    }

    @Test
    public void test(){

    }

}
