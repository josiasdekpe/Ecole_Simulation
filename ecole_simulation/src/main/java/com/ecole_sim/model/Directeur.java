package com.ecole_sim.model;

public class Directeur {

    private String username;
    private String password;

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

    @Override
    public String toString() {
        return "Directeur [username =" + username + "]";
    }

}
