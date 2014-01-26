package zinchenko.configs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.text.MessageFormat;

/**
 * User: zinchenko
 * Date: 26.01.14
 */
public class ProfessionInterceptor extends EmptyInterceptor {

    private static final Log LOG = LogFactory.getLog(ProfessionInterceptor.class);

    @Override
    public boolean onSave(Object entity, Serializable id,
                          Object[] state, String[] propertyNames,
                          Type[] types) {

        LOG.debug(MessageFormat.format("Was saving: {0}", entity));

        return false;
    }
}
