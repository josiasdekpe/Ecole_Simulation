package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoAdmin {

    private Map<Integer, Directeur> directeursMap; // Utilisation d'une Map pour stocker les directeurs par leur ID
    private Map<Integer, Matiere> matieresMap; // Utilisation d'une Map pour stocker les matières par leur ID
    private Map<Integer, Creneau> creneauxMap; // Utilisation d'une Map pour stocker les créneaux par leur ID

    public DaoAdmin() {
        this.directeursMap = new HashMap<>(); // Initialisation de la Map pour les directeurs
        this.matieresMap = new HashMap<>(); // Initialisation de la Map pour les matières
        this.creneauxMap = new HashMap<>(); // Initialisation de la Map pour les créneaux
    }

    public void insertDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getId(), directeur); // Ajout du directeur à la Map
    }

    public Directeur selectDirecteurById(int id) {
        return directeursMap.get(id); // Récupération du directeur par son ID depuis la Map
    }

    public void updateDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getId(), directeur); // Mise à jour du directeur dans la Map
    }

    public void deleteDirecteur(int id) {
        directeursMap.remove(id); // Suppression du directeur de la Map par son ID
    }

    public void insertMatiere(Matiere matiere) {
        matieresMap.put(matiere.getId(), matiere); // Ajout de la matière à la Map
    }

    public void insertCreneau(Creneau creneau) {
        creneauxMap.put(creneau.getId(), creneau); // Ajout du créneau à la Map
    }

    // Autres méthodes CRUD pour les matières, les créneaux, etc.
}
