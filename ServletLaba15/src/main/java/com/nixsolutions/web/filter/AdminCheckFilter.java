package com.nixsolutions.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Filter which checks an abonent. If an abonent is admin role then he go to
 * advance. Else he come back to login page.
 * 
 * @author zinchenko
 * 
 */
public class AdminCheckFilter implements Filter {

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        HttpSession session = request.getSession();

        Principal principal = (Principal) session
                .getAttribute(Principal.PRINCIPAL);

        System.out.println("princ " + principal);

        if (principal == null) {
            request.getRequestDispatcher("/loginPage.jsp").forward(req, res);
        } else if (principal.isUserInRole(Principal.ADMIN_ROLE)) {
            chain.doFilter(req, res);
        } else {
            request.getRequestDispatcher("/loginPage.jsp").forward(req, res);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // TODO Auto-generated method stub

    }

}
