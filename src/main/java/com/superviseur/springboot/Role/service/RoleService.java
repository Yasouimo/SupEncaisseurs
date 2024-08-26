package com.superviseur.springboot.Role.service;

import java.util.List;
import com.superviseur.springboot.Role.model.Role;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    void saveRole(Role role);
    void deleteRole(Long id);

    // Add method to find a role by name
    Role findByNom(String nom);
}
