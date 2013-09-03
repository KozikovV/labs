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

        s.append("\n<table id=\"users-table\" class=\"" + cssClass + "\">\n");

        s.append("<tr>\n");
        s.append("    <th>Login</th>\n");
        s.append("    <th>First name</th>\n");
        s.append("    <th>Last name</th>\n");
        s.append("    <th>Age</th>\n");
        s.append("    <th>Role</th>\n");
        s.append("    <th>Actions</th>\n");
        s.append("</tr>\n\n\n");

        for (User user : users) {
            // <tr>
            s.append("<tr id=\"tr"+ user.getId() +"\">\n");

            // <td>
            s.append("    <td id=\"login\">");
            s.append(user.getLogin());
            s.append("</td>\n");

            // <td>
            s.append("    <td id=\"firstName\">");
            s.append(user.getFirstName());
            s.append("</td>\n");

            // <td>
            s.append("    <td id=\"lastName\">");
            s.append(user.getLastName());
            s.append("</td>\n");

            // <td>
            s.append("    <td id=\"age\">");
            s.append(user.getAge());
            s.append("</td>\n");

            // <td>
            s.append("    <td id=\"role\">");
            s.append(user.getRole().getName());
            s.append("</td>\n");

            // <td>
            s.append("<td>");
            s.append("<a href=\"#\" onclick=\"edit(this)\">Edit</a>");
            s.append("  <a href=\"#\" onclick=\"del(this)\">Delete</a>");
//            s.append("<a href=\""
//                    + ((HttpServletRequest) pageContext.getRequest())
//                            .getContextPath() + "/editUser.htm?lg="
//                    + user.getLogin() + "\">Edit</a>");
//            s.append("  <a href=\""
//                    + ((HttpServletRequest) pageContext.getRequest())
//                            .getContextPath() + "/deleteUser.htm?id="
//                    + user.getId()
//                    + "\" onclick=\"return confirm('Delete ?');\" >Delete</a>");
            s.append("</td>\n");

            s.append("</tr>\n\n\n");
        }

        s.append("</table>\n");

        try {
            out.println(s.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
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
