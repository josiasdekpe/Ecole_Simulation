package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Matiere;

public interface AdminService {

	  public void addDirecteur(Directeur directeur);

	  public void removeDirecteur(Directeur directeur);
	  
	  public Directeur getDirecteurById(int id);
	  
	  public void addMatiere(Matiere matiere);
	  
	  public void addCreneau(Creneau creneau);
	  
	}

	