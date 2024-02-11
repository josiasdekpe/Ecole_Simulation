package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoCreneau {

    private Map<Integer, Creneau> creneauxMap; // Utilisation d'une Map pour stocker les créneaux par leur ID

    public DaoCreneau() {
        this.creneauxMap = new HashMap<>(); // Initialisation de la Map pour les créneaux
    }

    public void insertCreneau(Creneau creneau) {
        // Génération d'un ID pour le créneau
        int newId = generateNewId();
        creneau.setId(newId);

        // Ajout du créneau à la Map avec son ID comme clé
        creneauxMap.put(newId, creneau);
    }

    public void updateCreneau(Creneau creneau) {
        // Mise à jour du créneau dans la Map
        creneauxMap.put(creneau.getId(), creneau);
    }

    public Creneau getCreneauById(int id) {
        // Récupération du créneau à partir de l'ID dans la Map
        return creneauxMap.get(id);
    }

    public List<Creneau> getCreneauxByEnseignant(String usernameEnseignant) {
        // Implémentation de la logique pour récupérer les créneaux d'un enseignant par son ID
        List<Creneau> creneauxEnseignant = new ArrayList<>();
        for (Creneau creneau : creneauxMap.values()) {
            if (creneau.getEnseignant().getUsername() == usernameEnseignant) {
                creneauxEnseignant.add(creneau);
            }
        }
        return creneauxEnseignant;
    }

    public void deleteCreneau(int id) {
        // Suppression du créneau de la Map par son ID
        creneauxMap.remove(id);
    }

    public List<Creneau> getCreneaux() {
        return new ArrayList<>(creneauxMap.values()); // Récupération de tous les enseignants depuis la Map
    }    
    
    // Méthode pour générer un nouvel ID unique pour un créneau
    private int generateNewId() {
        // On trouve le plus grand ID actuel et on l'incrémente de 1 pour obtenir un nouvel ID unique
        int maxId = 0;
        for (int id : creneauxMap.keySet()) {
            if (id > maxId) {
                maxId = id;
            }
        }
        return maxId + 1;
    }
    
}
