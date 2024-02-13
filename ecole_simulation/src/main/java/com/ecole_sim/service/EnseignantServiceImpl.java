package com.ecole_sim.service;

import java.util.Date;


import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public class EnseignantServiceImpl implements EnseignantService {

    private DaoEnseignant daoEnseignant;
    private DaoCreneau daoCreneau;
    private DaoMatiere daoMatiere;
    
    
    public EnseignantServiceImpl() {
    	this(new DaoEnseignant(),new DaoCreneau(),new DaoMatiere());
    }
    
    public EnseignantServiceImpl(DaoEnseignant daoEnseignant, DaoCreneau daoCreneau, DaoMatiere daoMatiere) {
        this.daoEnseignant = daoEnseignant;
        this.daoCreneau = daoCreneau;
        this.daoMatiere = daoMatiere;
    }

    public void updateCreneau(Creneau creneau) {
        daoCreneau.updateCreneau(creneau);
    }

	public void peutenseignerMatiere(String enseignantUsername, String matiereNom) {
        Enseignant enseignant = daoEnseignant.getEnseignantByUsername(enseignantUsername);
        Matiere matiere = daoMatiere.getMatiereByName(matiereNom);
        matiere.addEnseignant(enseignant);
    }
	
    public void enseigneMatiere(String enseignantUsername, String matiereNom, Date date, String plageHoraire) {
        Enseignant enseignant = daoEnseignant.getEnseignantByUsername(enseignantUsername);
        Matiere matiere = daoMatiere.getMatiereByName(matiereNom);

        
        // Vérifier si l'enseignant, la matière et le créneau existent
        if (enseignant != null && matiere != null) {
            // Vérifier si l'enseignant est déjà affecté à la matière
            if (!matiere.isEnseignedBy(enseignant)) {
                // Ajouter l'enseignant à la liste des enseignants affectés à la matière
                matiere.addEnseignant(enseignant);
            }
            
            Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant);
            // Ajouter le créneau à la liste des créneaux 
            daoCreneau.insertCreneau(creneau);
        }
    }
	
	public DaoMatiere getDaoMatiere() {
        return daoMatiere;
    }
	public DaoEnseignant getDaoEnseignant() {
        return daoEnseignant;
    }
	public DaoCreneau getDaoCreneau() {
		return daoCreneau;
	}
}
