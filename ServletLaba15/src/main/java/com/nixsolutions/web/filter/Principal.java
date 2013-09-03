package com.nixsolutions.web.filter;

/**
 * The class which contains an information about current abonent.
 * 
 * @author zinchenko
 * 
 */
public class Principal {

    public static final String PRINCIPAL = "principal";

    public static final String ADMIN_ROLE = "Admin";
    public static final String USER_ROLE = "User";

    private String name;
    private String role;

    /**
     * Constructor with parameters.
     * 
     * @param name
     *            name of abonent
     * @param role
     *            role of abonent
     */
    public Principal(String name, String role) {
        super();
        this.name = name;
        this.role = role;
    }

    /**
     * Get name.
     * 
     * @param name
     */
    public String getName() {
        return name;
    }

    /**
     * Get role.
     * 
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * Check role of this user.
     * 
     * @param r
     *            asking role
     * @return {@code true} if this user have role declared as {@code r}
     */
    public boolean isUserInRole(final String r) {
        return role.equals(r);
    }

    @Override
    public String toString() {
        return "Principal [name=" + name + ", role=" + role + "]";
    }

}
