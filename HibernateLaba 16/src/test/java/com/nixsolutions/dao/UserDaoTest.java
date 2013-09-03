package com.nixsolutions.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.junit.Test;

import com.nixsolutions.domain.Role;
import com.nixsolutions.domain.User;

public class UserDaoTest extends DatabaseTestCase {

    private UserDao userDao = new HibernateUserDao();

    private Role[] roles = new Role[2];
    private User[] users = new User[2];

    private int numberRoleRow;
    private int numberUserRow;

    public UserDaoTest(String name) {
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
        users[0] = new User(101L, "newFN1", "newLN1", "log1dfyuk",
                "qwe@asd.as", "pass1", format.parse("2000-07-12"), new Role(2L,
                        "newRole222"));
        users[1] = new User(102L, "newFN2", "newLN2", "log2dfgs",
                "asd@fgc.asd", "pass2", format.parse("2011-07-12"), new Role(
                        3L, "newRole2333"));

        super.setUp();
    }

    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream(
                "src/test/resources/dataset.xml"));
    }

    @Override
    protected IDatabaseConnection getConnection() throws Exception {
        Class.forName("org.h2.Driver");
        Connection jdbcConnection = DriverManager.getConnection(
                "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", "test", "test");

        IDatabaseConnection dbUnitConnection = new DatabaseConnection(
                jdbcConnection);
        DatabaseConfig dbConfig = dbUnitConnection.getConfig();
        dbConfig.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
                new H2DataTypeFactory());

        return dbUnitConnection;
    }

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = userDao.findAll();
        assertEquals(numberUserRow, users.size());
    }

    @Test
    public void testSaveUser() {

        try {
            userDao.create(null);
            fail("should be threw NullPointerException when pass null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be threw NullPointerException when pass null");
        }

        userDao.create(users[0]);
        assertEquals(numberUserRow + 1, userDao.findAll().size());

    }

    @Test
    public void testUpdateUser() throws Exception {

        try {
            userDao.update(null);
            fail("should be throw NullPointerException if role null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be throw NullPointerException if role null");
        }

        try {
            userDao.update(users[0]);
            fail("should be throw Exception if role " + "doesn't exist");
        } catch (Exception e) {

        }

        users[0].setId(1L);
        users[0].setRole(getExpectedRoleFromDataSet(1));
        userDao.update(users[0]);
        User user = userDao.findByLogin(users[0].getLogin());

        // JDBCUser expectedUser = getExpectedUserFromDataSet(0);

        assertEquals(users[0], user);

    }

    @Test
    public void testGetUserByLogin() throws Exception {

        try {
            userDao.findByLogin(null);
            fail("should be throw NullPointerException if put null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be throw NullPointerException if put null");
        }

        assertNull(userDao.findByLogin(users[1].getLogin()));

        User expectedUser = getExpectedUserFromDataSet(0);

        User user = userDao.findByLogin(expectedUser.getLogin());
        assertEquals(expectedUser, user);
    }

    @Test
    public void testGetUserByEmail() throws Exception {

        try {
            userDao.findByEmail(null);
            fail("should be throw NullPointerException if put null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be throw NullPointerException if put null");
        }

        assertNull(userDao.findByEmail(users[1].getEmail()));

        User expectedUser = getExpectedUserFromDataSet(0);

        User user = userDao.findByEmail(expectedUser.getEmail());
        assertEquals(expectedUser, user);
    }

    @Test
    public void testDeleteUser() throws Exception {

        try {
            User user = null;
            userDao.remove(user);
            fail("should be throw NullPointerException if pass null");
        } catch (NullPointerException e) {
        } catch (Exception e) {
            fail("should be throw NullPointerException if pass null");
        }

        try {
            userDao.remove(users[0]);
            fail("should be throw IllegalArgumentException if user with "
                    + "this id doesn't exist");
        } catch (Exception e) {

        }

        userDao.remove(getExpectedUserFromDataSet(1));
        List<User> u = userDao.findAll();
        assertEquals(numberUserRow - 1, u.size());

    }

    private User getExpectedUserFromDataSet(int id) throws Exception {
        IDataSet dataSet = getDataSet();
        ITable userTable = dataSet.getTable("user");
        ITable roleTable = dataSet.getTable("role");

        User expectedUser = new User();
        expectedUser
                .setId(Long.parseLong((String) userTable.getValue(id, "id")));
        expectedUser
                .setFirstName((String) userTable.getValue(id, "first_name"));
        expectedUser.setLastName((String) userTable.getValue(id, "last_name"));
        expectedUser.setLogin((String) userTable.getValue(id, "login"));
        expectedUser.setEmail((String) userTable.getValue(id, "email"));
        expectedUser.setPassword((String) userTable.getValue(id, "password"));
        String bd = (String) userTable.getValue(id, "birth_date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(bd);
        expectedUser.setBirthDate(date);

        int numberOfRole = Integer.parseInt((String) userTable.getValue(id,
                "role_id"));

        Long id1 = Long.parseLong((String) roleTable.getValue(numberOfRole - 1,
                "id"));
        String name1 = (String) roleTable.getValue(numberOfRole - 1, "name");
        Role role = new Role(id1, name1);
        expectedUser.setRole(role);
        return expectedUser;
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
