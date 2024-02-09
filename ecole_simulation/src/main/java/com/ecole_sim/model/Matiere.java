package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.List;

public class Matiere {

    private int id;
    private String nom;
    private Directeur directeur;
    private List<Enseignant> enseignants = new ArrayList<>(); // Liste des enseignants de la matière

    public Matiere(String nom, Directeur directeur) {
        this.nom = nom;
        this.directeur = directeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Directeur getDirecteur() {
        return directeur;
    }

    public void setDirecteur(Directeur directeur) {
        this.directeur = directeur;
    }

    // Obtenir les enseignants de la matière
    public List<Enseignant> getEnseignants() {
        return enseignants;
    }

    // Ajouter un enseignant à la matière
    public void addEnseignant(Enseignant enseignant) {
        enseignants.add(enseignant);
    }

    // Vérifier si un enseignant enseigne cette matière
    public boolean isEnseignedBy(Enseignant enseignant) {
        return enseignants.contains(enseignant);
    }

    @Override
    public String toString() {
        return "Matiere [id=" + id + ", nom=" + nom + ", directeur=" + directeur + "]";
    }
}
