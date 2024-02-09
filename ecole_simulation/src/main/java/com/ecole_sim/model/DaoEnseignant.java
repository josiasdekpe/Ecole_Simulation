package com.ecole_sim.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoEnseignant {

    private Map<Integer, Enseignant> enseignantsMap; // Utilisation d'une Map pour stocker les enseignants par leur ID

    public DaoEnseignant() {
        this.enseignantsMap = new HashMap<>(); // Initialisation de la Map
    }

    public void insertEnseignant(Enseignant enseignant) {
        enseignantsMap.put(enseignant.getId(), enseignant); // Ajout de l'enseignant à la Map
    }

    public void updateEnseignant(Enseignant enseignant) {
        enseignantsMap.put(enseignant.getId(), enseignant); // Mise à jour de l'enseignant dans la Map
    }

    public void deleteEnseignant(int id) {
        enseignantsMap.remove(id); // Suppression de l'enseignant de la Map par son ID
    }

    public Enseignant getEnseignantById(int id) {
        return enseignantsMap.get(id); // Récupération de l'enseignant par son ID depuis la Map
    }

    public List<Enseignant> getEnseignants() {
        return new ArrayList<>(enseignantsMap.values()); // Récupération de tous les enseignants depuis la Map
    }

    public List<Creneau> getCreneaux(int enseignantId) {
        Enseignant enseignant = enseignantsMap.get(enseignantId);
        if (enseignant != null) {
            return enseignant.getCreneaux(); // Retourne les créneaux de l'enseignant s'il existe
        } else {
            return new ArrayList<>(); // Retourne une liste vide si aucun enseignant correspondant n'est trouvé
        }
    }
}
