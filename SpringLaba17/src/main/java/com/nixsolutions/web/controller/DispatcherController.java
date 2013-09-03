package com.nixsolutions.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The controller which dispatch users to hello or lisUsers page.
 * 
 * @author zinchenko
 * 
 */
@Controller
public class DispatcherController {

    private static final String ROLE_ADMIN = "ROLE_ADMIN";
    private static final String ROLE_USER = "ROLE_USER";

    /**
     * Dispatch user to hello page if it has USER role. Dispatch user to list
     * users page if it has ADMIN role.
     * 
     * @param request
     *            object of HttpServletRequest
     * @return view name
     */
    @RequestMapping("/dispatcher")
    public String disp(final HttpServletRequest request) {

        if (request.isUserInRole(ROLE_USER)) {
            return "hello";
        } else if (request.isUserInRole(ROLE_ADMIN)) {
            return "redirect:listUsers.htm";
        }

        throw new RuntimeException(
                "Don't rnow this role. Chech your security settings.");

    }

    /**
     * Redirect to login.
     * 
     * @param model
     *            model
     * @return view name
     */
    @RequestMapping("/login")
    public String login(final ModelMap model) {

        return "loginPage";
    }
}
