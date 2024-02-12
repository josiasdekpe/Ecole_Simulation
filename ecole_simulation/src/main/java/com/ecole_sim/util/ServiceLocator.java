package com.ecole_sim.util;

import com.ecole_sim.service.AdminServiceImpl;
import com.ecole_sim.service.DirecteurService;
import com.ecole_sim.service.DirecteurServiceImpl;
import com.ecole_sim.service.EnseignantService;
import com.ecole_sim.service.EnseignantServiceImpl;
import com.ecole_sim.service.AdminService;
public class ServiceLocator {

    private static final AdminService adminService = new AdminServiceImpl();
    private static final DirecteurService directeurService = new DirecteurServiceImpl();
    private static final EnseignantService enseignantService = new EnseignantServiceImpl();
    // Méthode pour récupérer l'instance de AdminService à partir de la configuration de l'application
    public static AdminService getAdminService() {
        return adminService;
    }
        // Ici, vous pouvez implémenter la logique pour récupérer l'instance de AdminService
        // Peut-être à partir d'une fabrique, d'une injection de dépendance ou d'une autre méthode de configuration
    public static DirecteurService getDirecteurService() {
        return directeurService;
    }
        
    public static EnseignantService getEnseignantService() {
        return enseignantService;        
    }
}
