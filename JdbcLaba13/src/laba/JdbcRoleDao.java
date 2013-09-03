package laba;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * DAO for Role.
 * 
 * @author zinchenko
 * 
 */
public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {

    /**
     * Constructor wit parameters.
     * 
     * @param url
     *            URL DB
     * @param username
     *            user name
     * @param password
     *            password
     * @param driverName
     *            full name Driver
     * 
     */
    public JdbcRoleDao(final String uri, final String username,
            final String password, final String driverName) {
        super(uri, username, password, driverName);
    }

    /**
     * Constructor which take object of class XMLPropertyLoader as parameter.
     * 
     * @param pl
     *            object of class XMLPropertyLoader
     */
    public JdbcRoleDao(final XMLPropertyLoader pl) {

        super(pl.getUrl(), pl.getUsername(), pl.getPassword(), pl.getDriver());

    }

    /**
     * Creating new role.
     * 
     * @param role
     *            new role
     */
    @Override
    public void create(final Role role) {

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("insert into role (name) values (?)");
            ps.setString(1, role.getName());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
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
     * Updating role.
     * 
     * @param role
     *            role for updating
     */
    @Override
    public void update(final Role role) {
        if (role == null) {
            throw new NullPointerException();
        }

        if (!isRoleById(role.getId())) {
            throw new IllegalArgumentException();
        }

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("UPDATE role SET name = ? where id = ?");
            ps.setString(1, role.getName());
            ps.setLong(2, role.getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
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
     * Removing role.
     * 
     * @param role
     *            for removing.
     * 
     */
    @Override
    public void remove(final Role role) {
        if (isRoleReferenced(role.getId())) {
            throw new IllegalArgumentException();
        }

        if (!isRoleById(role.getId())) {
            throw new IllegalArgumentException();
        }

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection.prepareStatement("DELETE FROM role WHERE id = ?");
            ps.setLong(1, role.getId());
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
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
     * Check referenced role with this ID.
     * 
     * @param idRole
     *            ID of role
     * @return true if referenced
     */
    private boolean isRoleReferenced(final Long idRole) {

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
                    .prepareStatement("SELECT * FROM user WHERE role_id = ?");
            ps.setLong(1, idRole);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
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
     * Check contain DB role with this ID.
     * 
     * @param idRole
     *            ID of role
     * @return true if contain
     */
    private boolean isRoleById(final Long id) {

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection.prepareStatement("SELECT * FROM role WHERE id = ?");
            ps.setLong(1, id);
            ResultSet res = ps.executeQuery();
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (ps != null && !ps.isClosed()) {
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
     * Find role by this name
     * 
     * @param nameRole
     *            name of role for finding.
     * @return role
     */
    @Override
    public Role findByName(final String nameRole) {

        if (nameRole == null) {
            throw new NullPointerException();
        }

        Role role = null;

        PreparedStatement ps = null;
        try {
            createConnection();
            ps = connection
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
                if (ps != null && !ps.isClosed()) {
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
