package com.ecole_sim.util;

import com.ecole_sim.model.DaoAdmin;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.DaoDirecteur;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;

public class DaoLocator {

    private static final DaoAdmin daoAdmin = new DaoAdmin();
    private static final DaoDirecteur daoDirecteur = new DaoDirecteur();
    private static final DaoEnseignant daoEnseignant = new DaoEnseignant();
    private static final DaoMatiere daoMatiere = new DaoMatiere();
    private static final DaoCreneau daoCreneau = new DaoCreneau();    
    // Méthode pour récupérer l'instance de AdminService à partir de la configuration de l'application
    public static DaoAdmin getDaoAdmin() {
        return daoAdmin;
    }
        // Ici, vous pouvez implémenter la logique pour récupérer l'instance de AdminService
        // Peut-être à partir d'une fabrique, d'une injection de dépendance ou d'une autre méthode de configuration
    public static DaoDirecteur getDaoDirecteur() {
        return daoDirecteur;
    }
        
    public static DaoEnseignant getDaoEnseignant() {
        return daoEnseignant;        
    }
    
    public static DaoMatiere getDaoMatiere() {
        return daoMatiere;        
    }
    
    public static DaoCreneau getDaoCreneau() {
        return daoCreneau;        
    }
}
