package com.ecole_sim.service;

import java.util.Date;


public interface EnseignantService {
    
    void updateCreneau(int creneauId);

	void peutenseignerMatiere(int enseignantId, int matiereId);

	void enseigneMatiere(int enseignantId, int matiereId, Date date, String PlageHoraire);    
}
