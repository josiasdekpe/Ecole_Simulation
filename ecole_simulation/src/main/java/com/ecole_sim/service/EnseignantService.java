package com.ecole_sim.service;

import java.util.Date;
import java.util.List;

import com.ecole_sim.model.Creneau;

public interface EnseignantService {

    List<Creneau> getCreneaux(int enseignantId);
    
    void setDisponibilite(int enseignantId, Date date);
    
    List<Date> getDisponibilites(int enseignantId);
}
