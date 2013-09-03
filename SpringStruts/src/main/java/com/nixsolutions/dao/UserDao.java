package com.nixsolutions.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nixsolutions.domain.User;

/**
 * DAO для объектов User.
 * 
 * @author zinchenko
 * 
 */
@Component
public interface UserDao {

    /**
     * Внесение нового User в БД.
     * 
     * @param user
     *            пользователь
     * 
     * @throws NullPointerException
     *             если user null
     * 
     */
    void create(User user);

    /**
     * Обновление User в БД.
     * 
     * @param user
     *            пользователь
     * 
     * @throws NullPointerException
     *             если user null
     * @throws IllegalArgumentException
     *             если user нет в БД
     * 
     */
    void update(User user);

    /**
     * Удаление User в БД.
     * 
     * @param user
     *            пользователь
     * 
     * @throws NullPointerException
     *             если user null
     * @throws IllegalArgumentException
     *             если user нет в БД
     * 
     */
    void remove(User user);

    /**
     * Удаление User в БД.
     * 
     * @param id
     *            ID пользователя
     * 
     * @throws NullPointerException
     *             если user null
     * @throws IllegalArgumentException
     *             если user нет в БД
     * 
     */
    void remove(Long id);

    /**
     * Получение всех пользователей.
     * 
     * @return пользователи.
     */
    List<User> findAll();

    /**
     * Получение по логину.
     * 
     * @param login
     *            логин
     * @return пользователь
     * @throws IllegalArgumentException
     *             ули такого полозователя нет в БД
     * 
     */
    User findByLogin(String login);

    /**
     * Получение по Е-mail
     * 
     * @param email
     *            Е-mail
     * @return пользователь
     */
    User findByEmail(String email);

    

}
