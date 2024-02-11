package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoDirecteur {

    private Map<String, Directeur> directeursMap; // Utilisation d'une Map pour stocker les directeurs par leur ID

    public DaoDirecteur() {
        this.directeursMap = new HashMap<>(); // Initialisation de la Map
        // Ajout d'un directeur par défaut avec un ID 1
        Directeur defaultDirecteur = new Directeur("directeur0", "directeur0");
        directeursMap.put(defaultDirecteur.getUsername(), defaultDirecteur);
    }

    public void insertDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getUsername(), directeur); // Ajout du directeur à la Map
    }

    public void updateDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getUsername(), directeur); // Mise à jour du directeur dans la Map
    }

    public void deleteDirecteur(String username) {
        directeursMap.remove(username); // Suppression du directeur de la Map par son ID
    }

    public Directeur getDirecteurByUsername(String username) {
        return directeursMap.get(username); // Récupération du directeur par son ID depuis la Map
    }

    public List<Directeur> getDirecteurs() {
        return new ArrayList<>(directeursMap.values()); // Récupération de tous les enseignants depuis la Map
    }
}
