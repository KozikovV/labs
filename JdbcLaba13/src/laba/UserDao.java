package laba;

import java.util.List;

/**
 * DAO for User entity.
 * 
 * @author zinchenko
 * 
 */
public interface UserDao {

    /**
     * Creating new User.
     * 
     * @param user
     *            user
     * 
     * @throws NullPointerException
     *             if user null
     * 
     */
    void create(User user);

    /**
     * Updating a user.
     * 
     * @param user
     *            user
     * 
     * @throws NullPointerException
     *             if user null
     * @throws IllegalArgumentException
     *             if DB hasn't this user.
     * 
     */
    void update(User user);

    /**
     * Removing user.
     * 
     * @param user
     *            user
     * 
     * @throws NullPointerException
     *             if user null
     * @throws IllegalArgumentException
     *            if DB hasn't this user.
     * 
     */
    void remove(User user);

    /**
     * Finding all users.
     * 
     * @return users.
     */
    List<User> findAll();

    /**
     * finding user by login.
     * 
     * @param login
     *            login
     * @return user
     */
    User findByLogin(String login);

    /**
     * Finding user by Е-mail
     * 
     * @param email
     *            Е-mail
     * @return user
     */
    User findByEmail(String email);
}
