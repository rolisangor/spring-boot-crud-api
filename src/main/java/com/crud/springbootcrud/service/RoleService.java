package com.crud.springbootcrud.service;

import com.crud.springbootcrud.exception.RoleNotFoundException;
import com.crud.springbootcrud.model.Role;

import java.util.Collection;

public interface RoleService {

    Role saveRole(Role role);
    Role getRoleByName(String name) throws RoleNotFoundException;
    Collection<Role> getAllRole();
    boolean existByName(String name);
}
