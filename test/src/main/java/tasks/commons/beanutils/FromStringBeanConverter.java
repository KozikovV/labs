package tasks.commons.beanutils;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.MessageFormat;

/**
 * User: zinchenko
 * Date: 05.01.14
 */
public class FromStringBeanConverter implements Converter {

    private static final Log LOG = LogFactory.getLog(FromStringBeanConverter.class);

    /**
     * @param aClass
     * @param o serialized Bean in form: "name|age"
     * @return
     */
    public Object convert(Class aClass, Object o) {
        String str = (String) o;
        LOG.trace(MessageFormat.format("Input string: {0}", str));
        String[] attrs = str.split("-");
        LOG.trace(MessageFormat.format("Splited string: name={0}, age={1}", attrs));
        Bean bean = new Bean();
        bean.setName(attrs[0]);
        bean.setAge(Integer.parseInt(attrs[1]));
        return bean;
    }
}
