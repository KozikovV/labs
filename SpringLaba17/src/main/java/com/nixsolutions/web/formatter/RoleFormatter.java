package com.nixsolutions.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.domain.Role;

/**
 * Formatter of Role entity.
 * 
 * @author zinchenko
 * 
 */
public class RoleFormatter implements Formatter<Role> {

    /**
     * DAO of role.
     */
    @Autowired
    private RoleDao roleDao;

    public String print(Role role, Locale arg1) {
        return role.getName();
    }

    public Role parse(String str, Locale arg1) throws ParseException {
        Role role = roleDao.findByName(str);
        return role;
    }

}
