package com.crud.springbootcrud.controller;

import com.crud.springbootcrud.model.role.Role;
import com.crud.springbootcrud.service.role_service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
@Slf4j
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/{name}")
    public ResponseEntity<Role> getRoleByName(@PathVariable String name) throws RoleNotFoundException {
        return ResponseEntity.ok(roleService.getRoleByName(name));
    }
}
