package com.superviseur.springboot.Agence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.superviseur.springboot.Agence.model.*;

@Repository
public interface AgenceRepository extends JpaRepository<Agence,Long> {

}
