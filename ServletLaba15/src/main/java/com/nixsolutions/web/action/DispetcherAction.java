package com.nixsolutions.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.nixsolutions.web.filter.Principal;

/**
 * An action for dispatch abonent when it requests index.jsp page. If an abonent
 * hasn't any role he is forwarded to the login page. If an abonent has User
 * role he is forwarted to the hello page. If an abonent has an admin role he is
 * forwarded to the list users page.
 * 
 * @author zinchenko
 * 
 */
public class DispetcherAction implements Action {

    @Override
    public String perform(HttpServletRequest request,
            HttpServletResponse response) {

        HttpSession session = request.getSession();

        Principal principal = (Principal) session
                .getAttribute(Principal.PRINCIPAL);

        System.out.println("principal from dispatcher : " + principal);

        if (principal == null) {
            return "login.htm";
        } else if (principal.isUserInRole(Principal.USER_ROLE)) {
            return "hello.jsp";
        } else if (principal.isUserInRole(Principal.ADMIN_ROLE)) {
            return "/listUsers.htm";
        }

        throw new RuntimeException("Don't know this role.");
    }

}
