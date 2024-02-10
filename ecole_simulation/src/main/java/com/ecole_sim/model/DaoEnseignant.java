package com.ecole_sim.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DaoEnseignant {

    private Map<Integer, Enseignant> enseignantsMap; // Utilisation d'une Map pour stocker les enseignants par leur ID

    public DaoEnseignant() {
        this.enseignantsMap = new HashMap<>(); // Initialisation de la Map
        // Ajout d'un enseignant par défaut avec un ID 1 lors de l'initialisation de la classe
        Enseignant defaultEnseignant = new Enseignant("Jane", "Doe");
        defaultEnseignant.setId(1);
        enseignantsMap.put(defaultEnseignant.getId(), defaultEnseignant);
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

    public void updateCreneau(Creneau creneau) {
        // Dans cette méthode, vous pouvez simplement mettre à jour le créneau de l'enseignant
        // Appel direct à la méthode updateCreneau de la classe Enseignant
        Enseignant enseignant = enseignantsMap.get(creneau.getEnseignant().getId());
        if (enseignant != null) {
            enseignant.updateCreneau(creneau);
        } else {
            // Gérer le cas où aucun enseignant correspondant n'est trouvé
        }
    }

}
