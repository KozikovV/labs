package test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import laba.JdbcRoleDao;
import laba.Role;
import laba.RoleDao;
import laba.User;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.Test;

public class JdbcRoleDaoTest extends DatabaseTestCase {

    private String driver = "org.h2.Driver";
    private String url = "jdbc:h2:mem:test";
    private String username = "test";
    private String password = "test";

    private RoleDao roleDao = new JdbcRoleDao(url, username, password, driver);

    private Role[] roles = new Role[2];
    private User[] users = new User[2];

    private int numberRoleRow;
    private int numberUserRow;

    public JdbcRoleDaoTest(String name) {
        super(name);
    }

    @Override
    protected void setUp() throws Exception {
        IDataSet dataSet = getDataSet();
        ITable roleTable = dataSet.getTable("role");
        numberRoleRow = roleTable.getRowCount();

        ITable userTable = dataSet.getTable("user");
        numberUserRow = userTable.getRowCount();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        roles[0] = new Role(101L, "newRole1");
        roles[1] = new Role(102L, "newRole2");
        users[0] = new User(101L, "newFN1", "newLN1", "log1", "qwe@asd.as",
                "pass1", format.parse("2000-07-12"), new Role(2L, "newRole222"));
        users[1] = new User(102L, "newFN2", "newLN2", "log2", "asd@fgc.asd",
                "pass2", format.parse("2011-07-12"),
                new Role(3L, "newRole2333"));

        super.setUp();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(
                "dataset.xml"));
    }

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", "test", "test");
        Statement createStatement = jdbcConnection.createStatement();
        Statement dropStatement = jdbcConnection.createStatement();

        try {
            dropStatement.executeUpdate(CreateTables.TABLES_DROP_SB.toString());
        } catch (Exception e) {
            // не ловим т.к при первом создании таблиц еще нечего удалять. и
            // будет вылетать исключение.
        }

        createStatement.executeUpdate(CreateTables.TABLES_CREATE_SB.toString());

        IDatabaseConnection dbUnitConnection = new DatabaseConnection(
                jdbcConnection);
        DatabaseConfig dbConfig = dbUnitConnection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());

        return dbUnitConnection;
    }

    @Test
    public void testSaveRole() throws Exception {
        try {
            roleDao.create(null);
            fail("should be threw NullPointerException when pass null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be threw NullPointerException when pass null");
        }

        roleDao.create(roles[0]);
        Role role = roleDao.findByName(roles[0].getName());
        assertEquals(roles[0].getName(), role.getName());

    }

    @Test
    public void testUpdateRole() throws Exception {
        try {
            roleDao.update(null);
            fail("should be throw NullPointerException if role null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be throw NullPointerException if role null");
        }

        try {
            roleDao.update(roles[0]);
            fail("should be throw IllegalArgumentException if role "
                    + "doesn't exist");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("should be throw IllegalArgumentException if role "
                    + "doesn't exist");
        }

        Role roleForUpdate = new Role(1L, roles[0].getName());

        roleDao.update(roleForUpdate);
        Role role = roleDao.findByName(roleForUpdate.getName());
        assertEquals(roleForUpdate, role);
    }

    @Test
    public void testFindByName() throws Exception {
        try {
            roleDao.findByName(null);
            fail("should be throw NullPointerException if role null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be throw NullPointerException if role null");
        }

        try {
            roleDao.findByName(roles[0].getName());
            fail("should be throw IllegalArgumentException if role "
                    + "doesn't exist");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("should be throw IllegalArgumentException if role "
                    + "doesn't exist");
        }

        Role expectedRole = getExpectedRoleFromDataSet(0);
        Role role = roleDao.findByName(expectedRole.getName());

        assertEquals(expectedRole, role);
    }

    @Test
    public void testDeleteRole() throws Exception {

        try {
            roleDao.remove(null);
            fail("should be throw NullPointerException if role null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be throw NullPointerException if role null");
        }

        try {
            roleDao.remove(roles[0]);
            fail("should be throw IllegalArgumentException if role with "
                    + "this id doesn't exist");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("should be throw IllegalArgumentException if role with "
                    + "this id doesn't exist");
        }

        try {
            Role referencedRole = getExpectedRoleFromDataSet(1);
            roleDao.remove(referencedRole);
            fail("should be throw IllegalArgumentException if role "
                    + "referenced with any user(s)");
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            fail("should be throw IllegalArgumentException if role "
                    + "referenced with any user(s)");
        }

        Role unreferencedRole = getExpectedRoleFromDataSet(2);

        assertEquals(unreferencedRole,
                roleDao.findByName(unreferencedRole.getName()));

        roleDao.remove(unreferencedRole);
        try {
            assertEquals(unreferencedRole,
                    roleDao.findByName(unreferencedRole.getName()));
            fail("must thows IllegalArgumentException because it was deleted");
        } catch (IllegalArgumentException e) {

        } catch (Exception e) {
            fail("must thows IllegalArgumentException because it was deleted");
        }

    }

    private Role getExpectedRoleFromDataSet(int id) throws Exception {
        IDataSet dataSet = getDataSet();
        ITable roleTable = dataSet.getTable("role");
        Long id1 = Long.parseLong((String) roleTable.getValue(id, "id"));
        String name1 = (String) roleTable.getValue(id, "name");
        Role expectedRole = new Role(id1, name1);
        return expectedRole;
    }

}
