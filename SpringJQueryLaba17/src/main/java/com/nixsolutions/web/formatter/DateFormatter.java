package com.nixsolutions.web.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;


public class DateFormatter implements Formatter<Date> {

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public String print(Date date, Locale arg1) {
        return sdf.format(date);
    }

    public Date parse(String string, Locale arg1) throws ParseException {
        return sdf.parse(string);
    }

}
