package tasks.commons.beanutils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * User: zinchenko
 * Date: 05.01.14
 */
public class BeanUtilsTest {

    private static final Log LOG = LogFactory.getLog(BeanUtilsTest.class);

    @Test
    public void testClone() throws InvocationTargetException,
            NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        Bean bean = new Bean();
        bean.setName("name");
        bean.setAge(10);

        Bean beanCloned = (Bean) BeanUtils.cloneBean(bean);

        assertEquals(bean.getName(), beanCloned.getName());
        assertEquals(bean.getAge(), beanCloned.getAge());
    }

    @Test
    public void testCopyProperties() throws InvocationTargetException, IllegalAccessException {
        Bean bean = new Bean();
        bean.setName("name");
        bean.setAge(10);
        Bean beanCloned = new Bean();

        assertEquals(null, beanCloned.getName());
        assertEquals(0, beanCloned.getAge());

        BeanUtils.copyProperties(beanCloned, bean);

        assertEquals(bean.getName(), beanCloned.getName());
        assertEquals(bean.getAge(), beanCloned.getAge());
    }

    @Test
    public void testCopyProperty() throws InvocationTargetException,
            IllegalAccessException {
        Bean bean = new Bean();
        bean.setName("name");
        bean.setAge(10);
        Bean beanCloned = new Bean();

        assertEquals(null, beanCloned.getName());
        assertEquals(0, beanCloned.getAge());

        BeanUtils.copyProperty(beanCloned, "name", bean);

        assertEquals(bean.getName(), beanCloned.getName());
        assertEquals(0, beanCloned.getAge());
    }

    @Test
    public void testDescribe() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Bean bean = new Bean();
        bean.setName("name");
        bean.setAge(10);

        Map map = BeanUtils.describe(bean);

        LOG.trace(map);
    }

}
