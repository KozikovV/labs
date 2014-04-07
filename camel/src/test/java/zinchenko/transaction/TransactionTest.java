package zinchenko.transaction;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath*:zinchenko/transaction/transaction-applicationContext.xml")
public class TransactionTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Autowired
    ConsumerTemplate consumerTemplate;

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Before
    public void before(){
//        jdbcTemplate.execute("create table PARTNER_METRIC "
//                + "( partner_id varchar(10), time_occurred varchar(20), " +
//                "status_code varchar(3), perf_time varchar(10) );");
//        jdbcTemplate.execute("INSERT INTO PARTNER_METRIC (partner_id, time_occurred, status_code, perf_time) VALUES ('123', '200911150815', '200', '4387');");
    }

    @Test
    public void test() throws InterruptedException {
        String xml = "<?xml version=\"1.0\"?><partner id=\"123\"><date>200911150815</date><code>200</code><time>4387</time></partner>";
        producerTemplate.sendBody("activemq:queue:partners", xml);

        Thread.sleep(1000);

//        String s = consumerTemplate.receiveBodyNoWait("activemq:queue:partners", String.class);
//        System.out.println(s);
    }



}
