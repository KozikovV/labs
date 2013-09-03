package com.nixsolutions.web.controller;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.gson.Gson;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.UserBean;
import com.nixsolutions.web.bean.UserForAddBean;

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

    @Autowired
    private RoleDao roleDao;

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

    /**
     * Get all roles.
     * 
     * @param response
     * @throws IOException
     * @throws JSONException
     */
    @RequestMapping("/getRoles")
    public void getRoles(HttpServletResponse response) throws IOException,
            JSONException {

        List<Role> roles = roleDao.findAll();
        JSONArray jsonArray = new JSONArray();
        for (Role role : roles) {
            jsonArray.put(new JSONObject(role));
        }

        JSONObject jsonRoles = new JSONObject();
        jsonRoles.put("roles", jsonArray);

        Writer writer = response.getWriter();
        writer.append(jsonRoles.toString());
        writer.flush();
        writer.close();
        return;
    }

    /**
     * Get user by id.
     * 
     * @param login
     * @param response
     * @throws JSONException
     * @throws IOException
     */
    @RequestMapping("/getUser")
    public void getUser(@RequestParam String login, HttpServletResponse response)
            throws JSONException, IOException {
        
        System.out.println("login "+login);

        User user = userDao.findByLogin(login);
        
        System.out.println("user ->> "+user);
        
        JSONObject jsonRole = new JSONObject(user.getRole());

        JSONObject jsonUser = new JSONObject();
        jsonUser.put("id", user.getId());
        jsonUser.put("login", user.getLogin());
        jsonUser.put("password", user.getPassword());
        jsonUser.put("email", user.getEmail());
        jsonUser.put("age", user.getAge());
        jsonUser.put("birthDate", user.getBirthDateFormat());
        jsonUser.put("firstName", user.getFirstName());
        jsonUser.put("lastName", user.getLastName());
        jsonUser.put("role", jsonRole);


        Writer writer = response.getWriter();
        writer.append(jsonUser.toString());
        writer.flush();
        writer.close();
        return;

        //Gson gson = new Gson();

//        Writer writer = response.getWriter();
//        writer.append(gson.toJson(UserBean.UserToUserBean(user)));
//        writer.flush();
//        writer.close();
//        return;
    }

    /**
     * Edit user.
     * 
     * @param userBean
     */
    @RequestMapping(value = "/editUserJQ", method = RequestMethod.POST)
    public void editUser(@ModelAttribute UserBean userBean) {
        
        System.out.println(userBean);

        userDao.update(UserBean.UserBeanToUser(userBean));

        return;

    }

    /**
     * Add user.
     * 
     * @param userBean
     */
    @RequestMapping(value = "/addUserJQ", method = RequestMethod.POST)
    public void addUser(@ModelAttribute UserForAddBean userBean) {

        userDao.create(UserBean.UserBeanToUser(userBean));

        return;

    }

    /**
     * Try find login in the DB.
     * 
     * @param login
     * @param response
     * @throws IOException
     * @throws JSONException
     */
    @RequestMapping(value = "/isLogin")
    public void isLogin(@RequestParam String login, HttpServletResponse response)
            throws IOException, JSONException {

        Writer writer = response.getWriter();
        JSONObject o = new JSONObject();

        try {
            if (userDao.findByLogin(login) != null) {
                o.put("isLogin", true);
                writer.append(o.toString());
            } else {
                o.put("isLogin", false);
                writer.append(o.toString());
            }
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * It try find email in DB. It is invoke when user is added.
     * 
     * @param email
     * @param response
     * @throws IOException
     * @throws JSONException
     */
    @RequestMapping(value = "/isEmail")
    public void isEmail(@RequestParam String email, HttpServletResponse response)
            throws IOException, JSONException {

        Writer writer = response.getWriter();
        JSONObject o = new JSONObject();

        try {
            if (userDao.findByEmail(email) != null) {
                o.put("isEmail", true);
                writer.append(o.toString());
            } else {
                o.put("isEmail", false);
                writer.append(o.toString());
            }
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * It try find email in DB. It is invoke when user is edited.
     * 
     * @param email
     *            email
     * @param id
     *            id of edited user
     * @param response
     *            response
     * @throws IOException
     * @throws JSONException
     */
    @RequestMapping(value = "/isEmailForEdit")
    public void isEmailForEdit(@RequestParam String email,
            @RequestParam Long id, HttpServletResponse response)
            throws IOException, JSONException {

        Writer writer = response.getWriter();
        JSONObject o = new JSONObject();

        User user = userDao.findByEmail(email);

        try {
            if (user != null) {
                if (id == user.getId()) {
                    o.put("isEmailForEdit", false);
                    writer.append(o.toString());
                } else {
                    o.put("isEmailForEdit", true);
                    writer.append(o.toString());
                }
            } else {
                o.put("isEmailForEdit", false);
                writer.append(o.toString());
            }
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * 
     * It is invoked for check captcha. This request is sended by AJAX.
     * 
     * @param captcha
     *            CAPTCHA value
     * @param request
     *            request
     * @param response
     *            response
     * @throws IOException
     * @throws JSONException
     */
    @RequestMapping(value = "/checkCaptcha")
    public void checkCaptcha(@RequestParam String captcha,
            HttpServletRequest request, HttpServletResponse response)
            throws IOException, JSONException {

        Writer writer = response.getWriter();
        JSONObject o = new JSONObject();

        try {
            if (validateCaptcha(request, captcha) == true) {
                o.put("checkCaptcha", true);
                writer.append(o.toString());
            } else {
                o.put("checkCaptcha", false);
                writer.append(o.toString());
            }
        } finally {
            writer.flush();
            writer.close();
        }
    }

    /**
     * It is invoked for remove user by AJAX.
     * 
     * @param login
     *            login of user.
     */
    @RequestMapping(value = "/removeUserJQ")
    public void removeUserJQ(@RequestParam String login) {
        User u = userDao.findByLogin(login);
        userDao.remove(u);
    }

    /**
     * Check of CAPTCHA value.
     * 
     * @param request
     *            object of HttpServletRequest
     * @param captcha
     *            CAPTCHA value
     */
    protected boolean validateCaptcha(HttpServletRequest request, String captcha) {
        String captchaId = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);

        if (!StringUtils.equalsIgnoreCase(captchaId, captcha)) {
            return false;
        } else {
            return true;
        }
    }

}
