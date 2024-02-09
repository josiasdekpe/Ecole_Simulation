package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.model.DaoCreneau;



public class DirecteurServiceImpl implements DirecteurService {

    private DaoMatiere daoMatiere;
    private DaoEnseignant daoEnseignant;
    private DaoCreneau daoCreneau; // Assume que vous avez injecté l'instance de DaoCreneau dans votre classe    

    public DirecteurServiceImpl(DaoMatiere daoMatiere, DaoEnseignant daoEnseignant, DaoCreneau daoCreneau) {
        this.daoMatiere = daoMatiere;
        this.daoEnseignant = daoEnseignant;
        this.daoCreneau = daoCreneau;
    }

    public void addMatiere(Matiere matiere) {
        daoMatiere.insertMatiere(matiere);
    }

    public void addEnseignant(Enseignant enseignant) {
        daoEnseignant.insertEnseignant(enseignant);
    }

    public void assignEnseignantToMatiere(Enseignant enseignant, Matiere matiere) {
        matiere.addEnseignant(enseignant);
    }

    public void updateCreneau(Creneau creneau) {
        // Appelez la méthode appropriée de votre DAO pour mettre à jour le créneau
        daoCreneau.updateCreneau(creneau);
    }

}

