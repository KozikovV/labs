package zinchenko.transaction.oneOrchestrated;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.hibernate.SessionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zinchenko.transaction.oneOrchestrated.bean.OrderBean;

import java.math.BigDecimal;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/zinchenko/transaction/oneOrchestrated/oneOrchestrated-appContext.xml")
public class CamelTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    ConsumerTemplate consumerTemplate;

    @Autowired
    SessionFactory sessionFactory;

    @Test
    public void test(){
        OrderBean orderBean = new OrderBean();
        orderBean.setFirstName("fn1");
        orderBean.setLastName("ln1");
        orderBean.setName("name1");
        orderBean.setPrice(122L);
        producerTemplate.sendBody("activemq:queue:order", orderBean);
    }

    @Test
    public void testErrorWhenSave() throws InterruptedException {
        OrderBean orderBean = new OrderBean();
//        orderBean.setFirstName("fn1");
//        orderBean.setLastName("ln1");
//        orderBean.setName("name1");
        orderBean.setPrice(122L);
        producerTemplate.sendBody("activemq:queue:order", orderBean);

//        Object answer = consumerTemplate.receiveBody("activemq:queue:order", 1000);
//        Assert.assertNull(answer);

        Thread.sleep(20000L);

//        Object answer2 = consumerTemplate.receiveBody("activemq:queue:ActiveMQ.DLQ", 1000);
//        Assert.assertNotNull(answer2);
    }



}
