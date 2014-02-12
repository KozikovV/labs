package zinchenko.jms.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;
import javax.jms.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("springJms-applicationContext.xml")
public class UTest {

    @Autowired
    OrderService orderService;

    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    public void test(){
        orderService.sendOrder(12, 23D);
    }

    @Test
    public void testReceive() throws JMSException {
        Message m = jmsTemplate.receive();
        System.out.println("- "+m.getJMSMessageID());
    }

}
