package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public interface DirecteurService {

	  public void addMatiere(Matiere matiere);
	  
	  public void addEnseignant(Enseignant enseignant);
	  
	  public void assignEnseignantToMatiere(Enseignant enseignant, Matiere matiere);
	  
	  public void updateCreneau(Creneau creneau);
	  
	}

	