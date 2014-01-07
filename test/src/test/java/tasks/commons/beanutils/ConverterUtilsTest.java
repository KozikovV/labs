package tasks.commons.beanutils;

import org.apache.commons.beanutils.ConvertUtilsBean;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: zinchenko
 * Date: 05.01.14
 */
public class ConverterUtilsTest {

    @Test
    public void test(){
        ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
        convertUtilsBean.register(new FromStringBeanConverter(), Bean.class);

        Bean bean = (Bean) convertUtilsBean.convert("myname-12", Bean.class);
        Assert.assertEquals("myname", bean.getName());
        Assert.assertEquals(12, bean.getAge());
    }

}