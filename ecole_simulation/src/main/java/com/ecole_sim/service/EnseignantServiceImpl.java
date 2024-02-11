package com.ecole_sim.service;

import java.util.Date;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public class EnseignantServiceImpl implements EnseignantService {

    private DaoEnseignant daoEnseignant;
    
    public EnseignantServiceImpl(DaoEnseignant daoEnseignant) {
        this.daoEnseignant = daoEnseignant;
    }

    public void updateCreneau(int creneauId) {
    	DaoCreneau daoCreneau = new DaoCreneau();
    	Creneau creneau = daoCreneau.getCreneauById(creneauId);
        daoEnseignant.updateCreneau(creneau);
    }

	public void peutenseignerMatiere(String enseignantUsername, int matiereId) {
        DaoEnseignant daoEnseignant = new DaoEnseignant();
        DaoMatiere daoMatiere = new DaoMatiere();
        Enseignant enseignant = daoEnseignant.getEnseignantByUsername(enseignantUsername);
        Matiere matiere = daoMatiere.getMatiereById(matiereId);
        enseignant.addMatiere(matiere);
    }
	
    public void enseigneMatiere(String enseignantUsername, int matiereId, Date date, String plageHoraire) {
        DaoEnseignant daoEnseignant = new DaoEnseignant();
        DaoMatiere daoMatiere = new DaoMatiere();
    	DaoCreneau daoCreneau = new DaoCreneau();
        Enseignant enseignant = daoEnseignant.getEnseignantByUsername(enseignantUsername);
        Matiere matiere = daoMatiere.getMatiereById(matiereId);

        
        // Vérifier si l'enseignant, la matière et le créneau existent
        if (enseignant != null && matiere != null) {
            // Vérifier si l'enseignant est déjà affecté à la matière
            if (!matiere.isEnseignedBy(enseignant)) {
                // Ajouter l'enseignant à la liste des enseignants affectés à la matière
                matiere.addEnseignant(enseignant);
            }
            if (!enseignant.peutenseignerMatiere(matiere)) {
                // Ajouter la matière à la liste des matières enseignées par l'enseignant 
                enseignant.addMatiere(matiere);}
            
            Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant);
            // Ajouter le créneau à la liste des créneaux 
            daoCreneau.insertCreneau(creneau);
        }
    }
	
}
