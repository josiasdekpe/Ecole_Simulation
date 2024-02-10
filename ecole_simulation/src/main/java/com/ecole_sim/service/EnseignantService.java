package com.ecole_sim.service;

import java.util.Date;
import java.util.List;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public interface EnseignantService {

    List<Creneau> getCreneaux(int enseignantId);
    
    void setDisponibilite(int enseignantId, Date date);
    
    List<Date> getDisponibilites(int enseignantId);
    
    void updateCreneau(Creneau creneau);

	void enseigneMatiere(Enseignant enseignant, Matiere matiere);    
}
