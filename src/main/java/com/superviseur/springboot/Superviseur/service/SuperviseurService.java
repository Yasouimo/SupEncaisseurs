package com.superviseur.springboot.Superviseur.service;

import java.util.List;
import com.superviseur.springboot.Superviseur.model.Superviseur;

public interface SuperviseurService {
    List<Superviseur> getAllSuperviseurs();
    Superviseur getSuperviseurById(Long id);
    void saveSuperviseur(Superviseur superviseur);
    void deleteSuperviseur(Long id);
    List<Superviseur> getSuperviseursByAgenceId(Long agenceId); // Add this method
}
