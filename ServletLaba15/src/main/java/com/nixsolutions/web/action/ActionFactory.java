package com.nixsolutions.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * A class for creating new actions by its name. 
 * 
 * @author zinchenko
 *
 */
public class ActionFactory {

    private static final Log LOG = LogFactory.getLog(ActionFactory.class);

    private static Map<String, Class> actions = getClasses();

    /**
     * The method for getting all possible actions at loading class.
     * 
     * @return
     */
    private static Map<String, Class> getClasses() {
        Map<String, Class> map = new HashMap<String, Class>();

        map.put("listUsers", ListUsersAction.class);
        map.put("editUser", EditUserAction.class);
        map.put("deleteUser", DeleteUserAction.class);
        map.put("addNewUser", AddNewUserAction.class);
        map.put("addNewUserForm", AddNewUserFormAction.class);
        map.put("editUserForm", EditUserFormAction.class);
        map.put("logout", LogoutAction.class);
        map.put("login", LoginAction.class);
        map.put("dispetcher", DispetcherAction.class);

        return map;
    }

    /**
     * Getting action.
     * 
     * @param name
     * @return action
     * @throws IllegalArgumentException
     *             if couldn't find action by this name.
     */
    public static Action getAction(String name) {

        LOG.trace("getAction() with: name = " + name);

        Action action = null;

        Class actionClass = null;
        if ((actionClass = actions.get(name)) == null) {
            throw new IllegalArgumentException("Don't know this action ("
                    + name + ").");
        }

        try {
            action = (Action) actionClass.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(e);
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        }

        return action;
    }

}
