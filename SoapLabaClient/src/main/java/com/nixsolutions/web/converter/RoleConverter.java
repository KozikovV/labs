package com.nixsolutions.web.converter;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

import com.nixsolutions.domain.Role;

/**
 * It converts the role type.
 * 
 * @author zinchenko
 *
 */
public class RoleConverter extends StrutsTypeConverter {

    private static final Log LOG = LogFactory.getLog(RoleConverter.class);

    @Override
    public Object convertFromString(Map arg0, String[] strings, Class arg2) {

        LOG.debug("convert from string: " + strings[0]);

        String[] values = strings[0].split(":");

        Role role = new Role();
        role.setId(Long.parseLong(values[0]));
        role.setName(values[1]);

        LOG.debug("converted role: " + role);

        return role;
    }

    @Override
    public String convertToString(Map arg0, Object object) {
        
        LOG.debug("convert to string: "+object);
        
        Role role = (Role) object;
        
        StringBuilder b = new StringBuilder();
        
        b.append(role.getId());
        b.append(":");
        b.append(role.getName());

        LOG.debug("converted : "+b.toString());
        
        return b.toString();
    }

}
