package com.ecole_sim.model;

import java.util.HashMap;
import java.util.Map;

public class DaoAdmin {

    private Map<String, Admin> adminsMap; // Utilisation d'une Map pour stocker les administrateurs par leur identifiant
    private Map<String, Directeur> directeursMap; // Utilisation d'une Map pour stocker les directeurs par leur ID
    private Map<Integer, Matiere> matieresMap; // Utilisation d'une Map pour stocker les matières par leur ID
    private Map<Integer, Creneau> creneauxMap; // Utilisation d'une Map pour stocker les créneaux par leur ID
    private Map<String, Enseignant> enseignantsMap; // Utilisation d'une Map pour stocker les enseignants par leur ID
    
    public DaoAdmin() {
        this.adminsMap = new HashMap<>(); // Initialisation de la Map pour les administrateurs
        this.directeursMap = new HashMap<>(); // Initialisation de la Map pour les directeurs
        this.matieresMap = new HashMap<>(); // Initialisation de la Map pour les matières
        this.creneauxMap = new HashMap<>(); // Initialisation de la Map pour les créneaux
        this.enseignantsMap = new HashMap<>(); // Initialisation de la Map pour les enseignants
        
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

    public void insertDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getUsername(), directeur); // Ajout du directeur à la Map
    }

    public void updateDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getUsername(), directeur); // Mise à jour du directeur dans la Map
    }

	public void insertEnseignant(Enseignant enseignant) {
	    enseignantsMap.put(enseignant.getUsername(), enseignant); // Ajout d'un enseignant à la Map
	}

    public void insertMatiere(Matiere matiere) {
        matieresMap.put(matiere.getId(), matiere); // Ajout de la matière à la Map
    }

    public void insertCreneau(Creneau creneau) {
        creneauxMap.put(creneau.getId(), creneau); // Ajout du créneau à la Map
    }

    // Méthodes CRUD pour les directeurs, matières, créneaux, etc.
}
