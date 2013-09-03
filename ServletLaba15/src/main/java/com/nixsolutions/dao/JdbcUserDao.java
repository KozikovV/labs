package com.nixsolutions.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;

/**
 * DAO for User entity.
 * 
 * @author zinchenko
 * 
 */
public class JdbcUserDao extends AbstractJdbcDao implements UserDao {

    private static UserDao userDao;

    /**
     * Private constructor.
     */
    private JdbcUserDao() {
    }

    /**
     * Getting JdbcUserDao.
     * 
     * @param url
     *            URL DB
     * @param username
     *            user name
     * @param password
     *            password
     * @param driverName
     *            Driver
     * @return this as singleton
     */
    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new JdbcUserDao();
        }
        return userDao;
    }

    /**
     * Creating new user.
     * 
     * @param user
     *            user for creating.
     */
    public void create(final User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("INSERT INTO user (first_name , "
                      + " last_name , login , email , password , birth_date , "
                      + " role_id ) VALUES ( ? , ? , ? , ? , ? , ? , ? )");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getEmail());
            ps.setString(5, user.getPassword());

            java.util.Date ubd = user.getBirthDate();
            Date bd = new Date(ubd.getTime());

            ps.setDate(6, bd);
            ps.setLong(7, user.getRole().getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Update user.
     * 
     * @param user
     *            user for updating.
     */
    public void update(final User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        if (!isUserById(user.getId())) {
            throw new IllegalArgumentException();
        }

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("UPDATE user SET first_name = ? , "
                            + " last_name = ? , login = ? , password = ? , "
                            + " birth_date = ? , role_id = ? WHERE id = ?");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getLogin());
            ps.setString(4, user.getPassword());

            java.util.Date ubd = user.getBirthDate();
            Date bd = new Date(ubd.getTime());

            ps.setDate(5, bd);
            ps.setLong(6, user.getRole().getId());
            ps.setLong(7, user.getId());
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    /**
     * Removing user.
     * 
     * @param user
     *            user for removing
     */
    public void remove(final User user) {

        if (!isUserById(user.getId())) {
            throw new IllegalArgumentException();
        }

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setLong(1, user.getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Removing user.
     * 
     * @param id
     *            id of user
     */
    public void remove(final Long id) {

        if (!isUserById(id)) {
            throw new IllegalArgumentException();
        }

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setLong(1, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Find all users.
     * 
     * @return list of users.
     */
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();

        Statement s = null;
        try {
            createConnection();
            s = connection.createStatement();
            ResultSet res = s.executeQuery("select * from user");
            while (res.next()) {
                list.add(userResultToObject(res));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (s != null) {
                    s.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        for (User user : list) {
            Role role = findRoleById(user.getRole().getId());
            user.setRole(role);
        }

        return list;
    }

    /**
     * Find user by id.
     * 
     * @param id
     *            id of user
     * @return user
     */
    public User findById(final Long id) {

        if (id == null) {
            throw new NullPointerException();
        }

        if (!isUserById(id)) {
            throw new IllegalArgumentException("");
        }

        User user = null;

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("SELECT * FROM user WHERE id = ?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            if (!res.next()) {
                throw new IllegalArgumentException();
            }
            user = userResultToObject(res);
            Role role = findRoleById(user.getRole().getId());
            user.setRole(role);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    /**
     * Find user by login.
     * 
     * @param login
     *            login of user
     * @return user
     */
    public User findByLogin(final String login) {

        if (login == null) {
            throw new NullPointerException();
        }

        if (!isUserByLogin(login)) {
            throw new IllegalArgumentException("");
        }

        User user = null;

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("SELECT * FROM user WHERE login = ?");
            ps.setString(1, login);
            ResultSet res = ps.executeQuery();
            if (!res.next()) {
                throw new IllegalArgumentException();
            }
            user = userResultToObject(res);
            Role role = findRoleById(user.getRole().getId());
            user.setRole(role);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    /**
     * Find user by e-mail.
     * 
     * @param email
     *            email of user
     * @return user
     */
    public User findByEmail(final String email) {
        if (email == null) {
            throw new NullPointerException();
        }

        if (!isUserByEmail(email)) {
            throw new IllegalArgumentException();
        }

        User user = null;

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("SELECT * FROM user WHERE email = ?");
            ps.setString(1, email);
            ResultSet res = ps.executeQuery();
            res.next();
            user = userResultToObject(res);
            Role role = findRoleById(user.getRole().getId());
            user.setRole(role);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    /**
     * Check database contains user by his ID.
     * 
     * @param id
     *            user id
     * @return {@code true} if exist
     */
    private boolean isUserById(final Long id) {
        
        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("SELECT * FROM user WHERE id = ?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Check database contains user by his login.
     * 
     * @param login
     *            login of user
     * @return {@code true} if exist
     */
    public boolean isUserByLogin(final String login) {
        
        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("SELECT * FROM user WHERE login = ?");
            ps.setString(1, login);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Check database contains user by his e-mail.
     * 
     * @param id
     *            e-mail of user
     * @return {@code true} if exist
     */
    public boolean isUserByEmail(final String email) {
        
        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("SELECT * FROM user WHERE email = ?");
            ps.setString(1, email);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    /**
     * Put values from ResultSet to JDBCRole by current row.
     * 
     * @param currentPosition
     *            source of data
     * @return object of class User
     */
    private User userResultToObject(final ResultSet currRow) {
        User user = new User();

        try {
            user.setId(currRow.getLong("id"));
            user.setFirstName(currRow.getString("first_name"));
            user.setLastName(currRow.getString("last_name"));
            user.setLogin(currRow.getString("login"));
            user.setEmail(currRow.getString("email"));
            user.setPassword(currRow.getString("password"));

            Date bd = currRow.getDate("birth_date");
            java.util.Date ubd = new java.util.Date(bd.getTime());

            user.setBirthDate(ubd);
            user.setRole(new Role(currRow.getLong("role_id")));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

    /**
     * Finding role by ID
     * 
     * @param id
     *            id of role
     * @return role
     */
    private Role findRoleById(final Long id) {

        if (id < 1) {
            throw new IllegalArgumentException();
        }

        Role role = null;

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("select * from role where id = ?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            if (!res.next()) {
                throw new IllegalArgumentException();
            }
            role = roleResultToObject(res);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return role;
    }

    /**
     * Put values from ResultSet to JDBCRole by current row.
     * 
     * @param currentPosition
     *            source of data
     * @return object of class Role
     */
    private Role roleResultToObject(final ResultSet currentPosition) {
        Role role = new Role();

        try {
            role.setId(currentPosition.getLong("id"));
            role.setName(currentPosition.getString("name"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return role;
    }

}
