package com.superviseur.springboot.Agence.model;


import java.util.Set;

import com.superviseur.springboot.Superviseur.model.Superviseur;

import jakarta.persistence.*;

@Entity
@Table(name="agences")
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nom_agence")
    private String nomAgence;

    @OneToMany(mappedBy = "agence")
    private Set <Superviseur> superviseurs;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomAgence() {
        return nomAgence;
    }

    public void setNomAgence(String nomAgence) {
        this.nomAgence = nomAgence;
    }

    public Set<Superviseur> getSuperviseurs() {
        return superviseurs;
    }

    public void setSuperviseurs(Set<Superviseur> superviseurs) {
        this.superviseurs = superviseurs;
    }


    

    
}


