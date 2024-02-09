package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoDirecteur {

    private Map<Integer, Directeur> directeursMap; // Utilisation d'une Map pour stocker les directeurs par leur ID

    public DaoDirecteur() {
        this.directeursMap = new HashMap<>(); // Initialisation de la Map
    }

    public void insertDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getId(), directeur); // Ajout du directeur à la Map
    }

    public void updateDirecteur(Directeur directeur) {
        directeursMap.put(directeur.getId(), directeur); // Mise à jour du directeur dans la Map
    }

    public void deleteDirecteur(int id) {
        directeursMap.remove(id); // Suppression du directeur de la Map par son ID
    }

    public Directeur getDirecteurById(int id) {
        return directeursMap.get(id); // Récupération du directeur par son ID depuis la Map
    }

    public List<Directeur> getDirecteurs() {
        return new ArrayList<>(directeursMap.values()); // Récupération de tous les directeurs depuis la Map
    }
}
