package laba;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Рабора с User При помощи Jdbc/
 * 
 * @author zinchenko
 * 
 */
public class JdbcUserDao extends AbstractJdbcDao implements UserDao {
    /**
     * Конструктор c параметрами.
     * 
     * @param url
     *            URL БД
     * @param username
     *            имя пользователя
     * @param password
     *            пароль
     * @param driverName
     *            полное имя драйвера
     * 
     */
    public JdbcUserDao(String uri, String username, String password,
            String driverName) {
        super(uri, username, password, driverName);
    }

    /**
     * Конструкрор принимающий объкт класса XMLPropertyLoader.
     * 
     * @param pl
     *            объкт класса XMLPropertyLoader
     */
    public JdbcUserDao(final XMLPropertyLoader pl) {

        super(pl.getUrl(), pl.getUsername(), pl.getPassword(), pl.getDriver());

    }

    public void create(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        try {
            createConnection();
            PreparedStatement ps = connection
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void update(User user) {

        if (user == null) {
            throw new NullPointerException();
        }

        if (!isUserById(user.getId())) {
            throw new IllegalArgumentException();
        }

        try {
            createConnection();
            PreparedStatement ps = connection
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void remove(User user) {

        if (!isUserById(user.getId())) {
            throw new IllegalArgumentException();
        }

        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setLong(1, user.getId());
            ps.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<User>();

        try {
            createConnection();
            Statement s = connection.createStatement();
            ResultSet res = s.executeQuery("select * from user");
            while (res.next()) {
                list.add(userResultToObject(res));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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

    public User findByLogin(String login) {

        if (login == null) {
            throw new NullPointerException();
        }

        if (!isUserByLogin(login)) {
            throw new IllegalArgumentException();
        }

        User user = null;

        try {
            createConnection();
            PreparedStatement s = connection
                    .prepareStatement("SELECT * FROM user WHERE login = ?");
            s.setString(1, login);
            ResultSet res = s.executeQuery();
            if (!res.next()) {
                throw new IllegalArgumentException();
            }
            user = userResultToObject(res);
            Role role = findRoleById(user.getRole().getId());
            user.setRole(role);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return user;
    }

    public User findByEmail(String email) {
        if (email == null) {
            throw new NullPointerException();
        }

        if (!isUserByEmail(email)) {
            throw new IllegalArgumentException();
        }

        User user = null;

        try {
            createConnection();
            PreparedStatement s = connection
                    .prepareStatement("SELECT * FROM user WHERE email = ?");
            s.setString(1, email);
            ResultSet res = s.executeQuery();
            res.next();
           
            user = userResultToObject(res);
            Role role = findRoleById(user.getRole().getId());
            user.setRole(role);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
     * Есть ли такой пользователь
     * 
     * @param id
     *            ID пользователя
     * @return {@code true} если есть
     */
    private boolean isUserById(Long id) {
        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM user WHERE id = ?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
     * Есть ли такой пользователь
     * 
     * @param login
     *            логин пользователя
     * @return {@code true} если есть
     */
    private boolean isUserByLogin(String login) {
        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM user WHERE login = ?");
            ps.setString(1, login);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
     * Есть ли такой пользователь
     * 
     * @param email
     *            E-mail пользователя
     * @return {@code true} если есть
     */
    private boolean isUserByEmail(String email) {
        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM user WHERE email = ?");
            ps.setString(1, email);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
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
     * Перекладывает знчения из ResultSet в Role в текущей строки.
     * 
     * @param currentPosition
     *            источник данных
     * @return преобразованный объект
     */
    private User userResultToObject(ResultSet currRow) {
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
     * Нахождение роли по номеру.
     * 
     * @param id
     *            номер
     * @return роль
     */
    private Role findRoleById(Long id) {

        if (id < 1) {
            throw new IllegalArgumentException();
        }

        Role role = null;

        try {
            createConnection();
            PreparedStatement ps = connection
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
     * Перекладывает знчения из ResultSet в JDBCRole в текущей строки.
     * 
     * @param currentPosition
     *            источник данных
     * @return преобразованный объект
     */
    private Role roleResultToObject(ResultSet currentPosition) {
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
