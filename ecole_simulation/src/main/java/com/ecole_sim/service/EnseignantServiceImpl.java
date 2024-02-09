package com.ecole_sim.service;

import java.util.Date;
import java.util.List;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoEnseignant;


public class EnseignantServiceImpl implements EnseignantService {

	  private DaoEnseignant daoEnseignant;

	  public EnseignantServiceImpl(DaoEnseignant daoEnseignant) {
		    this.daoEnseignant = daoEnseignant;
		  }
	@Override
	public List<Creneau> getCreneaux(int enseignantId) {
		// TODO Auto-generated method stub
	    return daoEnseignant.getCreneaux(enseignantId);
	}

	@Override
	public void setDisponibilite(int enseignantId, Date date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Date> getDisponibilites(int enseignantId) {
		// TODO Auto-generated method stub
		return null;
	}

}