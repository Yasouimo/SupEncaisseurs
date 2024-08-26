package com.superviseur.springboot.Superviseur.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.superviseur.springboot.Superviseur.model.Superviseur;
import com.superviseur.springboot.Superviseur.repository.SuperviseurRepository;

@Service
public class SuperviseurServiceImpl implements SuperviseurService {
    @Autowired
    private SuperviseurRepository superviseurRepository;

    @Override
    public List<Superviseur> getAllSuperviseurs() {
        return superviseurRepository.findAll();
    }

    @Override
    public Superviseur getSuperviseurById(Long id) {
        return superviseurRepository.findById(id).orElse(null);
    }

    @Override
    public void saveSuperviseur(Superviseur superviseur) {
        superviseurRepository.save(superviseur);
    }

    @Override
    public void deleteSuperviseur(Long id) {
        superviseurRepository.deleteById(id);
    }

    @Override
    public List<Superviseur> getSuperviseursByAgenceId(Long agenceId) {
        return superviseurRepository.findByAgenceId(agenceId);
    }
}
