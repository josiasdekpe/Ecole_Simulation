package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public interface DirecteurService {

    void addMatiere(Matiere matiere);
    
    void addEnseignant(Enseignant enseignant);
    
    void assignEnseignantToMatiere(Enseignant enseignant, Matiere matiere);
    
    void addCreneau(Creneau creneau);
    
    void updateCreneau(Creneau creneau);
    
    DaoEnseignant getDaoEnseignant();
    DaoMatiere getDaoMatiere();
    DaoCreneau getDaoCreneau();
}
