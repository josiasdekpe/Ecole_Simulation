package com.ecole_sim.model;

public class Admin {

    private static final String DEFAULT_USERNAME = "admin";
    private static final String DEFAULT_PASSWORD = "admin";

    private String username;
    private String password;

    public Admin() {
        this.username = DEFAULT_USERNAME;
        this.password = DEFAULT_PASSWORD;
    }

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
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
        return "Admin [username=" + username + ", password=" + password + "]";
    }

    // Changer le mot de passe
    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    // Vérifier si le mot de passe est correct
    public boolean authenticate(String password) {
        return this.password.equals(password);
    }
}
