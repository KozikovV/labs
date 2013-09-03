package com.nixsolutions.soaplaba.dao;

import java.util.List;

import com.nixsolutions.soaplaba.domain.User;

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
     *             if DB hasn't this user.
     * 
     */
    void remove(User user);

    /**
     * Removing user by id.
     * 
     * @param id
     *            id of user
     * 
     * @throws NullPointerException
     *             if user null
     * @throws IllegalArgumentException
     *             if DB hasn't this user.
     * 
     */
    void remove(Long id);

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
