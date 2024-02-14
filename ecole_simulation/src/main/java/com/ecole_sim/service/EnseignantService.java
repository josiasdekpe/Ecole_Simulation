package com.ecole_sim.service;

import java.util.Date;

import com.ecole_sim.model.Creneau;


public interface EnseignantService {

	void peutenseignerMatiere(String enseignantUsername, String matiereNom);

	void enseigneMatiere(String enseignantUsername, String matiereNom, Date date, String PlageHoraire);
	
    void updateCreneau(Creneau creneau);
    
}
