package com.superviseur.springboot.Agence.service;

import java.util.List;

import com.superviseur.springboot.Agence.model.*;;

public interface AgenceService {
    List<Agence> getAllAgences();
    Agence getAgenceById(Long id);
    Agence saveAgence(Agence agence);
    void deleteAgence(Long id);

}
