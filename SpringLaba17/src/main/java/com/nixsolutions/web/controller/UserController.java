package com.nixsolutions.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.bean.RegistrationVO;
import com.nixsolutions.web.bean.UserBean;
import com.nixsolutions.web.bean.UserForAddBean;

/**
 * Controller for User.
 * 
 * @author zinchenko
 * 
 */
@Controller
public class UserController {

    /**
     * Object of UserDao.
     */
    @Autowired
    private UserDao userDao;

    /**
     * Object of RoleDao.
     */
    @Autowired
    private RoleDao roleDao;

    /**
     * Method which take all roles from DB and redirect to the page for creating
     * new user.
     * 
     * @return object of class ModelAndView
     */
    @RequestMapping(value = "/addNewUser", method = RequestMethod.GET)
    public ModelAndView addNewUserForm() {

        List<Role> roles = roleDao.findAll();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roles", roles);
        map.put("user", new UserForAddBean());

        return new ModelAndView("addNewUser", map);
    }

    /**
     * The which called when add button on. In this method invoke validation
     * methods. If input data is correct new user add to DB. Otherwise come
     * back to the add user page.
     * 
     * @param userBean
     *            new user
     * @param result
     *            object of error message
     * @param model
     *            model
     * @param request
     *            object of class HttpServletRequest
     * @return string vith name of view
     */
    @RequestMapping(value = "/addNewUser", method = RequestMethod.POST)
    public String addNewUser(
            @Valid @ModelAttribute("user") UserForAddBean userBean,
            BindingResult result, ModelMap model, HttpServletRequest request) {

        System.out.println(userBean.getClass().toString() + "   " + userBean);

        checkLogginForAddUser(userBean, result);
        checkEmailForAddUser(userBean, result);
        checkPasswords(userBean, result);
        validateCaptcha(request, userBean, result);

        if (result.hasErrors()) {
            List<Role> roles = roleDao.findAll();
            model.put("roles", roles);
            return "addNewUser";
        } else {
            userDao.create(UserBean.UserBeanToUser(userBean));
            return "redirect:listUsers.htm";
        }

    }

    /**
     * The method which called before rendered page of edit user. It take all
     * roles from DB and redirect to the view.
     * 
     * @param login
     *            login of edited user.
     * @param model
     *            model
     * @return view name of edit user page
     */
    @RequestMapping(value = "/editUser", method = RequestMethod.GET)
    public String editUserForm(@RequestParam("lg") String login, ModelMap model) {

        List<Role> roles = roleDao.findAll();
        User user = userDao.findByLogin(login);

        model.put("roles", roles);
        model.put("user", UserBean.UserToUserBean(user));

        return "editUser";
    }

    /**
     * The which called when edit button on. In this method invoke validation
     * methods. If input data is correct user changed in DB. Otherwise come
     * back to the edit user page.
     * 
     * @param userBean
     *            new user
     * @param result
     *            object of error message
     * @param model
     *            model
     * @param request
     *            object of class HttpServletRequest
     * @return string vith name of view
     */
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public String editUser(@Valid @ModelAttribute("user") UserBean userBean,
            BindingResult result, ModelMap model, HttpServletRequest request) {

        checkEmailForEditUser(userBean, result);
        validateCaptcha(request, userBean, result);

        if (result.hasErrors()) {
            List<Role> roles = roleDao.findAll();
            model.put("roles", roles);
            return "editUser";
        } else {
            userDao.update(UserBean.UserBeanToUser(userBean));
            return "redirect:listUsers.htm";
        }
    }

    /**
     * The method for remove user from DB.
     * 
     * @param id
     *            ID of user
     * @return viev name of list users
     */
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("id") final Long id) {

        userDao.remove(id);

        return "redirect:listUsers.htm";
    }

    /**
     * The which called when registration link on.
     * 
     * @param model
     *            model
     * @return view name of registration page
     */
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registrationForm(final ModelMap model) {

        List<Role> roles = roleDao.findAll();
        model.put("roles", roles);
        model.put("user", new UserForAddBean());

        return "registration";
    }

    /**
     * The which called when registration button on. In this method invoke
     * methods. If input data is correct user add in DB and redirect to the
     * back to the registration user page.
     * 
     * @param userBean
     *            new user
     * @param result
     *            object with errors
     * @param model
     *            model
     * @param request
     *            object of class HttpServletRequest
     * @return view name
     */
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(
            @Valid @ModelAttribute("user") final UserForAddBean userBean,
            final BindingResult result, final ModelMap model,
            final HttpServletRequest request) {

        checkLogginForAddUser(userBean, result);
        checkEmailForAddUser(userBean, result);
        checkPasswords(userBean, result);
        validateCaptcha(request, userBean, result);

        if (result.hasErrors()) {
            List<Role> roles = roleDao.findAll();
            model.put("roles", roles);
            return "registration";
        } else {
            userDao.create(UserBean.UserBeanToUser(userBean));
            return "redirect:login.htm";
        }

    }

    /**
     * Check login when user add. This method try find any users with this
     * e-mail. If users were found it is means that this login can't used.
     * 
     * @param userBean
     *            user
     * @param result
     *            object of BindingResult
     */
    private void checkLogginForAddUser(UserForAddBean userBean,
            BindingResult result) {

        User user = userDao.findByLogin(userBean.getLogin());
        if (user != null) {
            result.rejectValue("login", "LoginIs.user.login");
        }

    }

    /**
     * Check e-mail when user add. If this e-mail is contained in the DB than
     * error message will be added into {@code result}.
     * 
     * @param userBean
     *            user
     * @param result
     *            object of BindingResult
     */
    private void checkEmailForAddUser(UserForAddBean userBean,
            BindingResult result) {
        User user = userDao.findByEmail(userBean.getEmail());
        if (user != null) {
            result.rejectValue("email", "EmailIs.user.email");
        }
    }

    /**
     * Check e-mail when user is changed. If this e-mail is contained other user
     * into DB than error message will be added into {@code result}.
     * 
     * @param userBean
     *            user
     * @param result
     *            object of BindingResult
     */
    private void checkEmailForEditUser(UserBean userBean, BindingResult result) {

        User user = userDao.findByEmail(userBean.getEmail());
        if (user != null && user.getId() != userBean.getId()) {
            result.rejectValue("email", "EmailIs.user.email");
        }

    }

    /**
     * Check equals of both passwords.
     * 
     * @param userBean
     *            user
     * @param result
     *            object of BindingResult
     */
    private void checkPasswords(UserForAddBean userBean, BindingResult result) {
        if (!userBean.getPassword().equals(userBean.getPasswordAgain())) {
            result.rejectValue("password", "PasswordsNotEquals.user.password");
        }
    }

    /**
     * Check of CAPTCHA value.
     * 
     * @param request
     *            object of HttpServletRequest
     * @param userBean
     *            user
     * @param result
     *            object of BindingResult
     */
    protected void validateCaptcha(HttpServletRequest request,
            UserBean userBean, BindingResult result) {
        String captchaId = (String) request.getSession().getAttribute(
                Constants.KAPTCHA_SESSION_KEY);
        String response = userBean.getCaptcha();

        if (!StringUtils.equalsIgnoreCase(captchaId, response)) {
            result.rejectValue("captcha", "InvalidCaptcha", "Invalid Entry");
        }
    }

}
