package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.List;

public class Directeur {

    private static int count = 0; // Compteur pour générer des identifiants uniques

    private int id;
    private String nom;
    private String prenom;
    private List<Matiere> matieres = new ArrayList<>();
    private List<Enseignant> enseignants = new ArrayList<>();

    public Directeur(String nom, String prenom) {
        this.id = ++count; // Incrémenter le compteur et affecter l'ID du directeur
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Matiere> getMatieres() {
        return matieres;
    }
  
    public void addMatiere(Matiere matiere) {
        matieres.add(matiere);
    }

    public void addEnseignant(Enseignant enseignant) {
        enseignants.add(enseignant);
    }

    public void removeEnseignant(Enseignant enseignant) {
        enseignants.remove(enseignant);
    }

    public Enseignant getEnseignantById(int id) {
        for (Enseignant e : enseignants) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    public List<Enseignant> getEnseignantsPourMatiere(Matiere matiere) {
        List<Enseignant> ens = new ArrayList<>();
        for (Enseignant e : enseignants) {
            if (e.peutenseignerMatiere(matiere)) {
                ens.add(e);
            }
        }
        return ens;
    }

    @Override
    public String toString() {
        return "Directeur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", matieres=" + matieres + "]";
    }

	public void setId(int Id) {
		// TODO Auto-generated method stub
		this.id = Id ;
	}

	public Matiere getMatiereByName(String nomMatiere) {
        for (Matiere m : matieres) {
            if (m.getNom() == nomMatiere) {
                return m;
            }
        }
        return null;
	}
}
