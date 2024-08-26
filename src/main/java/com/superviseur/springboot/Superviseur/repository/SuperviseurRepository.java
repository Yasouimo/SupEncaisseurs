package com.superviseur.springboot.Superviseur.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.superviseur.springboot.Superviseur.model.Superviseur;

public interface SuperviseurRepository extends JpaRepository<Superviseur, Long> {
    List<Superviseur> findByAgenceId(Long agenceId); // Add this method
}
