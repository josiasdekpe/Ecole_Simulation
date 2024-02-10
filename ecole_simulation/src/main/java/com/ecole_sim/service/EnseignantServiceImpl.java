package com.ecole_sim.service;

import java.util.Date;
import java.util.List;

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

    public List<Creneau> getCreneaux(int enseignantId) {
        return daoEnseignant.getCreneaux(enseignantId);
    }

    public void updateCreneau(int creneauId) {
    	DaoCreneau daoCreneau = new DaoCreneau();
    	Creneau creneau = daoCreneau.getCreneauById(creneauId);
        daoEnseignant.updateCreneau(creneau);
    }

	public void peutenseignerMatiere(int enseignantId, int matiereId) {
        DaoEnseignant daoEnseignant = new DaoEnseignant();
        DaoMatiere daoMatiere = new DaoMatiere();
        Enseignant enseignant = daoEnseignant.getEnseignantById(enseignantId);
        Matiere matiere = daoMatiere.getMatiereById(matiereId);
        enseignant.addMatiere(matiere);
    }
	
    public void enseigneMatiere(int enseignantId, int matiereId, Date date, String plageHoraire) {
        DaoEnseignant daoEnseignant = new DaoEnseignant();
        DaoMatiere daoMatiere = new DaoMatiere();
    	DaoCreneau daoCreneau = new DaoCreneau();
        Enseignant enseignant = daoEnseignant.getEnseignantById(enseignantId);
        Matiere matiere = daoMatiere.getMatiereById(matiereId);

        
        // Vérifier si l'enseignant, la matière et le créneau existent
        if (enseignant != null && matiere != null) {
            // Vérifier si l'enseignant enseigne déjà la matière
            if (!matiere.isEnseignedBy(enseignant)) {
                // Ajouter l'enseignant à la liste des enseignants enseignant la matière
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
