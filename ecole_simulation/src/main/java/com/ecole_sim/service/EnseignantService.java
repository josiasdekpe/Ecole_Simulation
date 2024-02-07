package com.ecole_sim.service;

import java.util.Date;
import java.util.List;

import com.ecole_sim.model.Creneau;

public interface EnseignantService {

	  public List<Creneau> getCreneaux(int enseignantId);
	  
	  public void setDisponibilite(int enseignantId, Date date);
	  
	  public List<Date> getDisponibilites(int enseignantId);
	  
	}
