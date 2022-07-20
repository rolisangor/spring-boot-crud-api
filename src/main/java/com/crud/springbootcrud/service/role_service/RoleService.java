package com.crud.springbootcrud.service.role_service;

import com.crud.springbootcrud.model.role.Role;
import javax.management.relation.RoleNotFoundException;
import java.util.Collection;

public interface RoleService {

    Role saveRole(Role role);
    Role getRoleByName(String name) throws RoleNotFoundException;
    Collection<Role> getAllRole();
    boolean existByName(String name);

}
