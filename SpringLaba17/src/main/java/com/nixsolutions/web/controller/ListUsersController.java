package com.nixsolutions.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.UserBean;

/**
 * Controller for list users.
 * 
 * @author zinchenko
 * 
 */
@Controller
public class ListUsersController {

    /**
     * Object of UserDao
     */
    @Autowired
    private UserDao userDao;

    /**
     * Find all users and redirect to the list users page.
     * 
     * @return
     */
    @RequestMapping("/listUsers")
    public ModelAndView listUsers() {

        List<User> users = userDao.findAll();

        return new ModelAndView("listUsers", "users", users);
    }

}
