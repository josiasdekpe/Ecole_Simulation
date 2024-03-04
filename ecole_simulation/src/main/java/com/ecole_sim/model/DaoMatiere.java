package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoMatiere {

    private Map<String, Matiere> matieresMap; // Utilisation d'une Map pour stocker les matières par leur ID

    public DaoMatiere() {
        this.matieresMap = new HashMap<>(); // Initialisation de la Map
        // Ajout d'une matière par défaut avec un ID 1 lors de l'initialisation de la classe
        Matiere defaultMatiere = new Matiere("Mathématiques");
        matieresMap.put(defaultMatiere.getNom(), defaultMatiere);
    }

    public void insertMatiere(Matiere matiere) {
        matieresMap.put(matiere.getNom(), matiere); // Ajout de la matière à la Map
    }

    public void updateMatiere(Matiere matiere) {
        matieresMap.put(matiere.getNom(), matiere); // Mise à jour de la matière dans la Map
    }

    public void deleteMatiere(String name) {
        matieresMap.remove(name); // Suppression de la matière de la Map par son name
    }

    public Matiere getMatiereByName(String name) {
        return matieresMap.get(name); // Récupération de la matière par son ID depuis la Map
    }
    
    public List<Matiere> getMatieres() {
        return new ArrayList<>(matieresMap.values()); // Récupération de toutes les matières depuis la Map
    }
}
