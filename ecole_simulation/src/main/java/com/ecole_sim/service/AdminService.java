package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Matiere;

public interface AdminService {

    void addDirecteur(Directeur directeur);

    void removeDirecteur(Directeur directeur);
    
    Directeur getDirecteurById(int id);
    
    void addMatiere(Matiere matiere);
    
    void addCreneau(Creneau creneau);
}
