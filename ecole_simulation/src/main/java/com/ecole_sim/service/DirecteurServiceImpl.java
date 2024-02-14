package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.util.DaoLocator;

public class DirecteurServiceImpl implements DirecteurService {

    private DaoMatiere daoMatiere = DaoLocator.getDaoMatiere();
    private DaoEnseignant daoEnseignant = DaoLocator.getDaoEnseignant();
    private DaoCreneau daoCreneau = DaoLocator.getDaoCreneau();

    public DirecteurServiceImpl() {
    	this(DaoLocator.getDaoMatiere(), DaoLocator.getDaoEnseignant(), DaoLocator.getDaoCreneau());
    }
    
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

    public void addCreneau(Creneau creneau) {
        daoCreneau.insertCreneau(creneau);
    }    
    
    public void updateCreneau(Creneau creneau) {
        daoCreneau.updateCreneau(creneau);
    }

}
