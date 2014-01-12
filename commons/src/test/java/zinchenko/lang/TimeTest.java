package tasks.commons.lang;

import zinchenko.lang.Time;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * User: zinchenko
 * Date: 04.01.14
 */
public class TimeTest {

    Time time = new Time();

    @Test
    public void testCeilingDate() throws Exception {
        Date date = DateUtils.parseDate("2002-03-28T13:45:01",
                new String[]{DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()});
        Date expectedDate = DateUtils.parseDate("2002-03-29T00:00:00",
                new String[]{DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()});

        Date result = time.ceilingDate(date);
        assertEquals(expectedDate, result);
    }

    @Test
    public void testTruncateDate() throws Exception {
        Date date = DateUtils.parseDate("2002-03-28T10:15:01",
                new String[]{DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()});
        Date expectedDate = DateUtils.parseDate("2002-03-28T00:00:00",
                new String[]{DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()});

        Date result = time.truncateDate(date);
        assertEquals(expectedDate, result);
    }

    @Test
    public void testAddOneDay() throws Exception {
        Date date = DateUtils.parseDate("2002-03-28T10:15:01",
                new String[]{DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()});
        Date expectedDate = DateUtils.parseDate("2002-03-29T10:15:01",
                new String[]{DateFormatUtils.ISO_DATETIME_FORMAT.getPattern()});

        Date result = time.addOneDay(date);
        assertEquals(expectedDate, result);
    }

    @Test
    public void testTimer(){
        time.timer();
    }

}
