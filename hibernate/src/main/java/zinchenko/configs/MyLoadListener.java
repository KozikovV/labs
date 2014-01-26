package zinchenko.configs;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.event.LoadEvent;
import org.hibernate.event.LoadEventListener;
import org.hibernate.event.def.DefaultLoadEventListener;

import java.text.MessageFormat;

/**
 * User: zinchenko
 * Date: 26.01.14
 */
public class MyLoadListener extends DefaultLoadEventListener {

    private static final Log LOG = LogFactory.getLog(MyLoadListener.class);

    @Override
    public void onLoad(LoadEvent event, LoadType loadType)
            throws HibernateException {
        LOG.debug(MessageFormat.format(
                "Was invoked hibernate load for entity: {0}",
                event.getEntityClassName()));
        super.onLoad(event, loadType);
    }
}
