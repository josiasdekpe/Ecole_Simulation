package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.List;

public class Enseignant {

    private String username;
    private String password;
    private List<Matiere> matieres = new ArrayList<>();
    private List<Creneau> creneaux = new ArrayList<>();

    
    public Enseignant(String username, String password) {
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
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

    @Override
    public String toString() {
        return "Enseignant [username=" + username + ", matieres=" + matieres + "]";
    }

    public Matiere getMatiereByName(String nomMatiere) {
        for (Matiere m : matieres) {
            if (m.getNom().equals(nomMatiere)) {
                return m; 
            }
        }
        return null;
    }

    public boolean peutenseignerMatiere(Matiere matiere) {
        return matieres.contains(matiere);
    }

    public void addCreneau(Creneau creneau) {
        if (!peutenseignerMatiere(creneau.getMatiere())) {
            throw new IllegalArgumentException("L'enseignant ne peut pas enseigner cette matière");
        }
        creneaux.add(creneau);
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }

    public void updateCreneau(Creneau creneau) {
        for (Creneau c : creneaux) {
            if (c.getId() == creneau.getId()) {
                c.setDate(creneau.getDate());
                c.setPlageHoraire(creneau.getPlageHoraire());
                // Mettez à jour d'autres champs du créneau si nécessaire
                return;
            }
        }
        // Si le créneau n'existe pas, ajoutez-le
        creneaux.add(creneau);
    }	
	
}
