package com.superviseur.springboot.Role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superviseur.springboot.Role.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findByNom(String nom);

}
