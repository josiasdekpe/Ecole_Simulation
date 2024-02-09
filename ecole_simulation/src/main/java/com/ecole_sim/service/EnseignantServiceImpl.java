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
        return daoEnseignant.getCreneaux(enseignantId);
    }

    @Override
    public void setDisponibilite(int enseignantId, Date date) {
        daoEnseignant.setDisponibilite(enseignantId, date);
    }

    @Override
    public List<Date> getDisponibilites(int enseignantId) {
        return daoEnseignant.getDisponibilites(enseignantId);
    }

    @Override
    public void updateCreneau(Creneau creneau) {
        daoEnseignant.updateCreneau(creneau);
    }
}
