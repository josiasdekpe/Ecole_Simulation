package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.util.DaoLocator;
import com.ecole_sim.model.DaoAdmin;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoDirecteur;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public class AdminServiceImpl implements AdminService {

    private DaoAdmin daoAdmin;
    private DaoDirecteur daoDirecteur;
    private DaoEnseignant daoEnseignant;
    private DaoMatiere daoMatiere;
    private DaoCreneau daoCreneau;
    
    public AdminServiceImpl() {
    	this(DaoLocator.getDaoAdmin(), DaoLocator.getDaoDirecteur(), DaoLocator.getDaoEnseignant(), DaoLocator.getDaoMatiere(), DaoLocator.getDaoCreneau());
    }
    
    public AdminServiceImpl(DaoAdmin daoAdmin, DaoDirecteur daoDirecteur, DaoEnseignant daoEnseignant, DaoMatiere daoMatiere, DaoCreneau daoCreneau) {
        this.daoAdmin = daoAdmin;    
        this.daoDirecteur = daoDirecteur;  
        this.daoEnseignant = daoEnseignant;  
        this.daoMatiere = daoMatiere;  
        this.daoCreneau = daoCreneau;  
    }

    public void addDirecteur(Directeur directeur) {
        daoDirecteur.insertDirecteur(directeur);
    }
    
    public void addMatiere(Matiere matiere) {
        daoMatiere.insertMatiere(matiere);
    }
    
    public void addCreneau(Creneau creneau) {
        daoCreneau.insertCreneau(creneau);
    }
    
    public void changeAdminPassword(String username, String newPassword) {
        // Méthode pour changer le mot de passe de l'administrateur par défaut
    	daoAdmin.updateAdminPassword(username, newPassword);
    }

	public void addEnseignant(Enseignant enseignant) {
		daoEnseignant.insertEnseignant(enseignant);
	}

}
