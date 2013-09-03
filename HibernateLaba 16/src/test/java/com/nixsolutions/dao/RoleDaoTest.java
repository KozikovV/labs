package com.nixsolutions.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.List;

import org.dbunit.DBTestCase;
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

public class RoleDaoTest extends DatabaseTestCase {

    private RoleDao roleDao = new HibernateRoleDao();

    private Role[] roles = new Role[2];
    private User[] users = new User[2];

    private int numberRoleRow;
    private int numberUserRow;

    public RoleDaoTest(String name) {
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
        List<Role> all = roleDao.findAll();
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
            fail("should be throw Exception if role " + "doesn't exist");
        } catch (Exception e) {

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

        assertNull(roleDao.findByName(roles[0].getName()));

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
            Role referencedRole = getExpectedRoleFromDataSet(1);
            roleDao.remove(referencedRole);
            fail("should be throw Exception if role "
                    + "referenced with any user(s)");
        } catch (Exception e) {

        }

        Role unreferencedRole = getExpectedRoleFromDataSet(2);

        assertEquals(unreferencedRole,
                roleDao.findByName(unreferencedRole.getName()));

        roleDao.remove(unreferencedRole);

        assertNull(roleDao.findByName(unreferencedRole.getName()));

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
