package com.crud.springbootcrud.service;

import com.crud.springbootcrud.exception.BadRequestException;
import com.crud.springbootcrud.exception.RoleNotFoundException;
import com.crud.springbootcrud.model.Role;
import com.crud.springbootcrud.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@Slf4j
//@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    @Override
    public Role saveRole(Role role) {
        if (existByName(role.getName())) throw new BadRequestException("Role: " + role.getName() + " already exist");
        return roleRepository.save(role);
    }

    @Transactional(readOnly = true)
    @Override
    public Role getRoleByName(String name)  {
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Role: " + name + " not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public Collection<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existByName(String name) {
        return roleRepository.existsByName(name);
    }
}
