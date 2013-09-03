package com.nixsolutions.web.converter;

import static org.junit.Assert.*;

import java.util.Date;

import org.apache.struts2.util.StrutsTypeConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.nixsolutions.domain.Role;

public class RoleConverterTest {

	private StrutsTypeConverter converter;

	private static final Role ROLE = new Role(1L, "name");

	private static final String[] ROLE_STRING = {"1:name"};

	@Before
	public void before(){
		
		converter = new RoleConverter();
		
	}

	@Test
	public void convertFromString() {

		assertEquals(ROLE, converter.convertFromString(null, ROLE_STRING, null));
		
	}

	@Test
	public void convertToString() {
		
		assertEquals(ROLE_STRING[0], converter.convertToString(null, ROLE));
		
	}

}
