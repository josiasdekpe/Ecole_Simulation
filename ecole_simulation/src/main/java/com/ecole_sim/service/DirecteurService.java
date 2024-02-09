package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public interface DirecteurService {

    void addMatiere(Matiere matiere);
    
    void addEnseignant(Enseignant enseignant);
    
    void assignEnseignantToMatiere(Enseignant enseignant, Matiere matiere);
    
    void updateCreneau(Creneau creneau);
}
