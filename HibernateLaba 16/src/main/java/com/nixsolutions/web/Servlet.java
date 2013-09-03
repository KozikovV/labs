package com.nixsolutions.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nixsolutions.web.action.Action;
import com.nixsolutions.web.action.ActionFactory;

/**
 * Main application servlet. It receives an action name, create action object and
 * perform created action. It uses "strategy" design pattern.
 * 
 * @author zinchenko
 * 
 */
public class Servlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nameAction = req.getServletPath();
        nameAction = nameAction.substring(1, nameAction.indexOf('.'));

        Action action = ActionFactory.getAction(nameAction);
        String goTo = action.perform(req, resp);

        req.getRequestDispatcher(goTo).forward(req, resp);
    }

}
