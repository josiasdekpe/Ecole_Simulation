package com.ecole_sim.service;

import java.util.List;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;

public class DirecteurServiceImpl implements DirecteurService {

    private DaoMatiere daoMatiere;
    private DaoEnseignant daoEnseignant;
    private DaoCreneau daoCreneau;

    public DirecteurServiceImpl(DaoMatiere daoMatiere, DaoEnseignant daoEnseignant, DaoCreneau daoCreneau) {
        this.daoMatiere = daoMatiere;
        this.daoEnseignant = daoEnseignant;
        this.daoCreneau = daoCreneau;
    }

    public void addMatiere(Matiere matiere) {
        daoMatiere.insertMatiere(matiere);
    }

    public void addEnseignant(Enseignant enseignant) {
        daoEnseignant.insertEnseignant(enseignant);
    }

    public void assignEnseignantToMatiere(Enseignant enseignant, Matiere matiere) {
        matiere.addEnseignant(enseignant);
    }

    public void addCreneau(Creneau creneau) {
        daoCreneau.insertCreneau(creneau);
    }    
    
    public void updateCreneau(Creneau creneau) {
        List<Matiere> matieres = daoMatiere.getMatieres();
        Matiere matiereDuDirecteur = getMatiereDuDirecteur(creneau, matieres);
        if (matiereDuDirecteur != null) {
            daoCreneau.updateCreneau(creneau);
        } else {
            throw new IllegalArgumentException("Ce créneau ne fait pas partie des créneaux de la matière gérée par ce directeur.");
        }
    }

    // Méthode privée pour récupérer la matière du directeur pour un créneau donné
    private Matiere getMatiereDuDirecteur(Creneau creneau, List<Matiere> matieres) {
        for (Matiere matiere : matieres) {
            if (creneau.getMatiere().equals(matiere)) {
                return matiere;
            }
        }
        return null; // Retourne null si le créneau n'appartient à aucune des matières gérées par le directeur
    }

}
