package laba;

/**
 * DAO для работы с ролями.
 * 
 * @author zinchenko
 *
 */
public interface RoleDao {

    /**
     * Добавление роли в БД.
     * @param role роль
     * 
     */
     void create(Role role);

    /**
     * Обновление роли.
     * @param role роль
     */
     void update(Role role);

    /**
     * Удаление роли.
     * 
     * @param role роль
     */
     void remove(Role role);
    
    /**
     * Нахождение роли по названию
     * @param nameRole название роли
     * @return роль
     */
     Role findByName(String nameRole);
}
