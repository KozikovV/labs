package zinchenko.lang;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.StopWatch;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import zinchenko.lang.beans.Person;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: zinchenko
 * Date: 04.01.14
 */
public class Time {

    private static final Log LOG = LogFactory.getLog(Time.class);

    public Date ceilingDate(Date date){
        LOG.trace(MessageFormat.format("Data for modifying: {0,date} {0,time}", date));
        Date result = DateUtils.ceiling(date, Calendar.DATE);
        LOG.trace(MessageFormat.format("Modified data: {0,date} {0,time}", result));
        return result;
    }

    public Date truncateDate(Date date){
        LOG.trace(MessageFormat.format("Data for modifying: {0,date} {0,time}", date));
        Date result = DateUtils.truncate(date, Calendar.DATE);
        LOG.trace(MessageFormat.format("Modified data: {0,date} {0,time}", result));
        return result;
    }

    public Date addOneDay(Date date){
        LOG.trace(MessageFormat.format("Data for modifying: {0,date} {0,time}", date));
        Date result = DateUtils.addDays(date, 1);
        LOG.trace(MessageFormat.format("Modified data: {0,date} {0,time}", result));
        return result;
    }

    public void timer() {
        LOG.trace("Was invoked method timer()");
        int n = 100000;
        LOG.trace(MessageFormat.format("There is will create object, fill one field and invoke toString method {0} times", n));
        StopWatch watcher = new StopWatch();
        String str = null;
        watcher.start();
        for (int i = 0; i < n; i++) {
            Person person = new Person();
            person.setName("name");
            str = person.toString();
        }
        watcher.stop();
        LOG.trace(MessageFormat.format("Was spent {0} milliseconds for simple toString() = {1}",
                watcher.getTime(), str));

        StopWatch watcherUsingBuilder = new StopWatch();
        String strUsingBuilder = null;
        watcherUsingBuilder.start();
        for (int i = 0; i < n; i++) {
            Person person = new Person();
            person.setName("name");
            strUsingBuilder = person.toStringUsingBuilder();
        }
        watcherUsingBuilder.stop();
        LOG.trace(MessageFormat.format("Was spent {0} milliseconds for operation toStringUsingBuilder() = {1}",
                watcherUsingBuilder.getTime(), strUsingBuilder));
    }

}
