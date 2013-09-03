package laba;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Доступ к Ролям через Jdbc/
 * 
 * @author zinchenko
 *
 */
public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {

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
    public JdbcRoleDao(String uri, String username, String password,
            String driverName) {
        super(uri, username, password, driverName);
    }

    /**
     * Конструкрор принимающий объкт класса XMLPropertyLoader.
     * 
     * @param pl
     *            объкт класса XMLPropertyLoader
     */
    public JdbcRoleDao(final XMLPropertyLoader pl) {

        super(pl.getUrl(), pl.getUsername(), pl.getPassword(), pl.getDriver());

    }
    
    public void create(Role role) {
        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("insert into role (name) values (?)");
            // ps.setLong(1, role.getId());
            ps.setString(1, role.getName());
            ps.executeUpdate();
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
    }


    public void update(Role role) {
        if (role == null) {
            throw new NullPointerException();
        }

        if (!isRoleById(role.getId())) {
            throw new IllegalArgumentException();
        }

        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("UPDATE role SET name = ? where id = ?");
            ps.setString(1, role.getName());
            ps.setLong(2, role.getId());
            ps.execute();

            // List<JDBCRole> list = getAllRoles();
            // System.out.println();
            // if(!ps.execute()){
            // throw new IllegalArgumentException();
            // }
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
    }


    public void remove(Role role) {
        if (isRoleReferenced(role.getId())) {
            throw new IllegalArgumentException();
        }

        if (!isRoleById(role.getId())) {
            throw new IllegalArgumentException();
        }

        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("DELETE FROM role WHERE id = ?");
            ps.setLong(1, role.getId());
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

    private boolean isRoleReferenced(Long idRole) {
        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM user WHERE role_id = ?");
            ps.setLong(1, idRole);
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

    private boolean isRoleById(Long id) {
        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("SELECT * FROM role WHERE id = ?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
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
        return false;
    }


    public Role findByName(String nameRole) {

        if (nameRole == null) {
            throw new NullPointerException();
        }

        Role role = null;

        try {
            createConnection();
            PreparedStatement ps = connection
                    .prepareStatement("select * from role where name = ?");
            ps.setString(1, nameRole);
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
