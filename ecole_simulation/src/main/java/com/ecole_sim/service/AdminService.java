package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Matiere;

public interface AdminService {

    // Ajoute un directeur à l'école
    void addDirecteur(Directeur directeur);

    // Supprime un directeur de l'école
    void removeDirecteur(Directeur directeur);
    
    // Récupère un directeur par son identifiant
    Directeur getDirecteurById(int id);
    
    // Ajoute une matière à l'école
    void addMatiere(Matiere matiere);
    
    // Ajoute un créneau à l'école
    void addCreneau(Creneau creneau);
    
    // Modifie le mot de passe de l'administrateur par défaut
    void changeAdminPassword(String username, String newPassword);
}

