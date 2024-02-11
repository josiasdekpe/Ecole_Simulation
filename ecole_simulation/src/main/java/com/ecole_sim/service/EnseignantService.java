package com.ecole_sim.service;

import java.util.Date;


public interface EnseignantService {
    
    void updateCreneau(int creneauId);

	void peutenseignerMatiere(String enseignantUsername, int matiereId);

	void enseigneMatiere(String enseignantUsername, int matiereId, Date date, String PlageHoraire);    
}
