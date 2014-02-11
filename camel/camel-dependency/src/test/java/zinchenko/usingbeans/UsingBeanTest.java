package zinchenko.usingbeans;

import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zinchenko.usingbeans.domains.*;

/**
 * User: zinchenko
 * Date: 02.02.14
 */
public class UsingBeanTest extends CamelSpringTestSupport {

    ApplicationContext applicationContext;

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        AbstractXmlApplicationContext abstractXmlApplicationContext =
                new ClassPathXmlApplicationContext("/zinchenko/usingbeans/usingBeanTest.xml");
        applicationContext = abstractXmlApplicationContext;
        return abstractXmlApplicationContext;
    }

    @Test
    public void testFindById(){
        Bean bean = template.requestBody("direct:getBeanById", 10L, Bean.class);
        Assert.assertEquals("name-10", bean.getName());
    }

    @Test
    public void testFindByName(){
        Bean bean = template.requestBody("direct:findByName", "name", Bean.class);
        Assert.assertEquals("name-name", bean.getName());
    }

}
