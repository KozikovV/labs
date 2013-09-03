package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.domain.Role;

/**
 * DAO для работы с ролями.
 * 
 * @author zinchenko
 * 
 */
public interface RoleDao {

    /**
     * Добавление роли в БД.
     * 
     * @param role
     *            роль
     * 
     */
    void create(Role role);

    /**
     * Обновление роли.
     * 
     * @param role
     *            роль
     */
    void update(Role role);

    /**
     * Удаление роли.
     * 
     * @param role
     *            роль
     */
    void remove(Role role);

    /**
     * Нахождение роли по названию
     * 
     * @param nameRole
     *            название роли
     * @return роль
     */
    Role findByName(String nameRole);
    
    Role findById(Long id);

    List<Role> findAll();
}
