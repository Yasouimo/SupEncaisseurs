package com.superviseur.springboot.Agence.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.superviseur.springboot.Agence.model.*;
import com.superviseur.springboot.Agence.repository.AgenceRepository;

@Service
public class AgenceServiceImpl implements AgenceService {
     @Autowired
    private AgenceRepository agenceRepository;

    @Override
    public List<Agence> getAllAgences() {
        return agenceRepository.findAll();
    }

    @Override
    public Agence getAgenceById(Long id) {
        return agenceRepository.findById(id).orElse(null);
    }

    @Override
    public Agence saveAgence(Agence agence) {
        return agenceRepository.save(agence);
    }

    @Override
    public void deleteAgence(Long id) {
        agenceRepository.deleteById(id);
    }

}
