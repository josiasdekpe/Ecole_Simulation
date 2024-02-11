package com.ecole_sim.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class DaoEnseignant {

    private Map<String, Enseignant> enseignantsMap; // Utilisation d'une Map pour stocker les enseignants par leur ID

    public DaoEnseignant() {
        this.enseignantsMap = new HashMap<>(); // Initialisation de la Map
        // Ajout d'un enseignant par défaut avec un ID 1 lors de l'initialisation de la classe
        Enseignant defaultEnseignant = new Enseignant("enseignant0", "enseignant0");
        enseignantsMap.put(defaultEnseignant.getUsername(), defaultEnseignant);
    }

    public void insertEnseignant(Enseignant enseignant) {
        enseignantsMap.put(enseignant.getUsername(), enseignant); // Ajout de l'enseignant à la Map
    }

    public void updateEnseignant(Enseignant enseignant) {
        enseignantsMap.put(enseignant.getUsername(), enseignant); // Mise à jour de l'enseignant dans la Map
    }

    public void deleteEnseignant(String username) {
        enseignantsMap.remove(username); // Suppression de l'enseignant de la Map par son ID
    }

    public Enseignant getEnseignantByUsername(String username) {
        return enseignantsMap.get(username); // Récupération de l'enseignant par son ID depuis la Map
    }

    public List<Enseignant> getEnseignants() {
        return new ArrayList<>(enseignantsMap.values()); // Récupération de tous les enseignants depuis la Map
    }

    public List<Creneau> getCreneaux(String enseignantUsername) {
        Enseignant enseignant = enseignantsMap.get(enseignantUsername);
        if (enseignant != null) {
            return enseignant.getCreneaux(); // Retourne les créneaux de l'enseignant s'il existe
        } else {
            return new ArrayList<>(); // Retourne une liste vide si aucun enseignant correspondant n'est trouvé
        }
    }

    public void updateCreneau(Creneau creneau) {
        // Dans cette méthode, vous pouvez simplement mettre à jour le créneau de l'enseignant
        // Appel direct à la méthode updateCreneau de la classe Enseignant
        Enseignant enseignant = enseignantsMap.get(creneau.getEnseignant().getUsername());
        if (enseignant != null) {
            enseignant.updateCreneau(creneau);
        } else {
            // Gérer le cas où aucun enseignant correspondant n'est trouvé
        }
    }

}
