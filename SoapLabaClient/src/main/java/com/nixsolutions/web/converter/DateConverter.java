package com.nixsolutions.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.validation.ValidationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * It converts the Date type.
 * 
 * @author zinchenko
 *
 */
public class DateConverter extends StrutsTypeConverter {

    private static final Log LOG = LogFactory.getLog(DateConverter.class);

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd");

    private static final String REGEX = "[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}";

    @Override
    public Object convertFromString(Map map, String[] string, Class clazz) {

        String str = string[0];
        LOG.debug("convert from string: " + str);

        if (!str.matches(REGEX)) {
            throw new ValidationException();
        }

        Date date = null;

        try {
            date = SIMPLE_DATE_FORMAT.parse(str);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        LOG.debug("converted: " + date);

        return date;
    }

    @Override
    public String convertToString(Map arg0, Object object) {

        LOG.debug("convert to string: " + object);

        String string = SIMPLE_DATE_FORMAT.format(object);

        LOG.debug("converted: " + string);

        return string;

    }

}
