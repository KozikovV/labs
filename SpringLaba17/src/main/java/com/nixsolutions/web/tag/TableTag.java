package com.nixsolutions.web.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.nixsolutions.domain.User;

/**
 * Tag for rendering table users.
 * 
 * @author zinchenko
 * 
 */
public class TableTag extends BodyTagSupport {

    /**
     * CSS class
     */
    private String cssClass;

    /**
     * list of users.
     */
    private List<User> users;

    @Override
    public int doEndTag() throws JspException {

        pageContext.getRequest().getServerName();

        JspWriter out = pageContext.getOut();

        StringBuilder s = new StringBuilder();

        s.append("<table class=\"" + cssClass + "\">");

        s.append("<tr>");
        s.append("<td>");
        s.append("Login");
        s.append("</td>");
        s.append("<td>");
        s.append("First name");
        s.append("</td>");
        s.append("<td>");
        s.append("Last name");
        s.append("</td>");
        s.append("<td>");
        s.append("Age");
        s.append("</td>");
        s.append("<td>");
        s.append("Role");
        s.append("</td>");
        s.append("<td>");
        s.append("Actions");
        s.append("</td>");
        s.append("</tr>");

        for (User user : users) {
            s.append("<tr>");
            s.append("<td>");
            s.append(user.getLogin());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getFirstName());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getLastName());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getAge());
            s.append("</td>");
            s.append("<td>");
            s.append(user.getRole().getName());
            s.append("</td>");
            s.append("<td>");
            s.append("<a href=\""
                    + ((HttpServletRequest) pageContext.getRequest())
                            .getContextPath() + "/editUser.htm?lg="
                    + user.getLogin() + "\">Edit</a>");
            // s.append("  <a href=\""
            // + ((HttpServletRequest) pageContext.getRequest())
            // .getContextPath() + "/deleteUser.htm?id="
            // + user.getId() + "\">Delete</a>");
            s.append("  <a href=\""
                    + ((HttpServletRequest) pageContext.getRequest())
                            .getContextPath() + "/deleteUser.htm?id="
                    + user.getId()
                    + "\" onclick=\"return confirm('Delete ?');\" >Delete</a>");
            s.append("</td>");
            s.append("</tr>");
        }

        s.append("</table>");

        try {
            out.println(s.toString());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return EVAL_PAGE;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

}
