package com.nixsolutions.web.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nixsolutions.web.Url;

/**
 * An action is invoked when user submits a logout link.
 * 
 * @author zinchenko
 * 
 */
public class LogoutAction implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }

        return Url.LOGIN_JSP;
    }

}
