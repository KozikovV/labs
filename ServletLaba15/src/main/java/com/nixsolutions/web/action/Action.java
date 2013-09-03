package com.nixsolutions.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * An action interface. It uses in main application servlet.
 * 
 * @author zinchenko
 *
 */
public interface Action {

	/**
	 * A perform method.
	 * 
	 * @param request object of class HttpServletRequest
	 * @param response object of class HttpServletResponse
	 * @return name of view for forward.
	 */
	String perform(HttpServletRequest request, HttpServletResponse response);

}
