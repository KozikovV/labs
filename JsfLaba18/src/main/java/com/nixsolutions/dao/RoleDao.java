package com.nixsolutions.dao;

import java.util.List;

import com.nixsolutions.domain.Role;

/**
 * DAO for Role entity.
 * 
 * @author zinchenko
 * 
 */
public interface RoleDao {

    /**
     * Adding a role.
     * 
     * @param role
     *            role
     * 
     */
    void create(Role role);

    /**
     * Updating a role.
     * 
     * @param role
     *            role
     */
    void update(Role role);

    /**
     * Removing a role.
     * 
     * @param role
     *            role
     */
    void remove(Role role);

    /**
     * Finding role by it name.
     * 
     * @param nameRole
     *            name of role
     * @return role
     */
    Role findByName(String nameRole);

    /**
     * Finding role by it ID.
     * 
     * @param nameRole
     *            ID of role
     * @return role
     */
    Role findById(Long id);

    /**
     * Find all roles.
     * 
     * @return all roles
     */
    List<Role> findAll();
}
