package m;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.dao.util.XMLPropertiesLoaderImpl;
import com.nixsolutions.dao.util.XMLPropertyLoader;
import com.nixsolutions.domain.User;
import com.nixsolutions.web.action.Action;
import com.nixsolutions.web.action.ListUsersAction;

public class Main {

    public static void main(String[] args) {
        
       
        
        UserDao userDao = JdbcUserDao.getUserDao();
        
        List<User> users = userDao.findAll();
        
        boolean b = userDao.isUserByLogin("x");

       
        System.out.println("sdf");
    }

}
