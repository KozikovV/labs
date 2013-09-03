package laba;

import java.util.List;

/**
 * DAO для объектов User.
 * 
 * @author zinchenko
 * 
 */
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
