package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.List;

public class Directeur {

    private String username;
    private String password;
    private List<Matiere> matieres = new ArrayList<>();
    private List<Enseignant> enseignants = new ArrayList<>();

    public Directeur(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public boolean authenticate(String password) {
        // Vérifie si le mot de passe fourni correspond au mot de passe enregistré pour le directeur
        return this.password.equals(password);
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Enseignant getEnseignantByUsername(String username) {
        for (Enseignant e : enseignants) {
            if (e.getUsername() == username) {
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
        return "Directeur [username =" + username + ", matieres=" + matieres + "]";
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
