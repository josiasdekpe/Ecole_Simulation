package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.List;

public class Matiere {

    private String nom;
    private List<Enseignant> enseignants = new ArrayList<>(); // Liste des enseignants de la matière

    public Matiere(String nom) {// Incrémenter le compteur et affecter l'ID de la matière
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
        return "Matiere [nom=" + nom + "]";
    }

	public void removeEnseignant(Enseignant enseignant) {
		// TODO Auto-generated method stub
		enseignants.remove(enseignant);
	}

}
