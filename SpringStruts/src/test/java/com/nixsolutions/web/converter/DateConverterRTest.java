package com.nixsolutions.web.converter;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.util.StrutsTypeConverter;
import org.junit.Before;
import org.junit.Test;

public class DateConverterRTest {
	
	private StrutsTypeConverter converter;
	
	private Date date;
	
	private String[] dateString = {"2001-11-11"};
	
	@Before
	public void before() throws ParseException{
		
		converter = new DateConverter();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date = sdf.parse(dateString[0]);
	}

	@Test
	public void testConvertFromString() {
		
		assertEquals(date, converter.convertFromString(null, dateString, null));
		
	}

	@Test
	public void testConvertToString() {

		assertEquals(dateString[0], converter.convertToString(null, date));
		
	}

}
