package com.ecole_sim.service;

import java.util.Date;


public interface EnseignantService {
    
    void updateCreneau(int creneauId);

	void peutenseignerMatiere(String enseignantUsername, String matiereNom);

	void enseigneMatiere(String enseignantUsername, String matiereNom, Date date, String PlageHoraire);    
}
