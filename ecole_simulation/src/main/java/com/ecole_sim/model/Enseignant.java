package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.List;

public class Enseignant {

    private String username;
    private String password;
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

    @Override
    public String toString() {
        return "Enseignant [username=" + username + "]";
    }

    public void addCreneau(Creneau creneau) {
        if (!creneau.getMatiere().isEnseignedBy(this)) {
            throw new IllegalArgumentException("L'enseignant ne peut pas enseigner cette matière");
        }
        creneaux.add(creneau);
    }

    public List<Creneau> getCreneaux() {
        return creneaux;
    }
	
}
