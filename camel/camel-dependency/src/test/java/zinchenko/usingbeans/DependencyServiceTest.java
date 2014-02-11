package zinchenko.usingbeans;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.spring.SpringCamelContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import zinchenko.usingbeans.domains.*;

/**
 * User: zinchenko
 * Date: 06.02.14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("dependencyServiceTest.xml")
public class DependencyServiceTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    CamelContext springCamelContext;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test(){
        ProducerTemplate producerTemplate = springCamelContext.createProducerTemplate();
        Person person = producerTemplate.requestBody("direct:findByName", "name", Person.class);

    }

}
