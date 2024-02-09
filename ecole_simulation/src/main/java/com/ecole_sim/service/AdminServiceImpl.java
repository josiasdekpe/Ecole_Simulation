package com.ecole_sim.service;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoAdmin;
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Matiere;

public class AdminServiceImpl implements AdminService {

    private DaoAdmin daoAdmin;

    public AdminServiceImpl(DaoAdmin daoAdmin) {
        this.daoAdmin = daoAdmin;
    }

    public void addDirecteur(Directeur directeur) {
        daoAdmin.insertDirecteur(directeur);
    }

    public void removeDirecteur(Directeur directeur) {
        daoAdmin.deleteDirecteur(directeur.getId());
    }

    public Directeur getDirecteurById(int id) {
         return daoAdmin.selectDirecteurById(id);
    }

    public void addMatiere(Matiere matiere) {
        daoAdmin.insertMatiere(matiere);
    }

    public void addCreneau(Creneau creneau) {
        daoAdmin.insertCreneau(creneau);
    }
}
