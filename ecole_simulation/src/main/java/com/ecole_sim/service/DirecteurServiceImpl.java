package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public class DirecteurServiceImpl implements DirecteurService {

    private DaoMatiere daoMatiere;
    private DaoEnseignant daoEnseignant;
    private DaoCreneau daoCreneau;

    public DirecteurServiceImpl() {
    	this(new DaoMatiere(), new DaoEnseignant(), new DaoCreneau());
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

	public DaoEnseignant getDaoEnseignant() {
        return daoEnseignant;
    }
	public DaoMatiere getDaoMatiere() {
        return daoMatiere;
    }
	public DaoCreneau getDaoCreneau() {
        return daoCreneau;
    }

}
