package com.ecole_sim.service;

import java.util.Date;
import java.util.List;

import com.ecole_sim.model.Creneau;

public interface EnseignantService {

    List<Creneau> getCreneaux(int enseignantId);
    
    void updateCreneau(int creneauId);

	void peutenseignerMatiere(int enseignantId, int matiereId);

	void enseigneMatiere(int enseignantId, int matiereId, Date date, String PlageHoraire);    
}
