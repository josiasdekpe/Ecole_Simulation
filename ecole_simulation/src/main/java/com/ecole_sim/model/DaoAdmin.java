package com.ecole_sim.model;

import java.util.HashMap;
import java.util.Map;

public class DaoAdmin {

    private Map<String, Admin> adminsMap; // Utilisation d'une Map pour stocker les administrateurs par leur identifiant
  
    public DaoAdmin() {
        this.adminsMap = new HashMap<>(); // Initialisation de la Map pour les administrateurs

        // Ajout de l'administrateur par défaut avec identifiant "admin" et mot de passe "admin"
        Admin defaultAdmin = new Admin("admin", "admin");
        adminsMap.put(defaultAdmin.getUsername(), defaultAdmin);
        
    }

    // Méthode pour récupérer l'administrateur par son identifiant
    public Admin selectAdminByUsername(String username) {
        return adminsMap.get(username);
    }

    // Méthode pour mettre à jour le mot de passe de l'administrateur
    public void updateAdminPassword(String username, String newPassword) {
        Admin admin = adminsMap.get(username);
        if (admin != null) {
            admin.setPassword(newPassword);
        }
    }

    // Méthodes CRUD pour les directeurs, matières, créneaux, etc.
}
