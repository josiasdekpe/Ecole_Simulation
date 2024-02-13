package com.ecole_sim.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.ecole_sim.model.Admin;
import com.ecole_sim.model.DaoAdmin;
import com.ecole_sim.model.DaoDirecteur;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.service.AdminService;
import com.ecole_sim.util.ServiceLocator;

/**
 * Servlet Filter implementation class AuthenticationFilter
 */
@WebFilter("/login")
public class AuthenticationFilter implements Filter {

    private AdminService adminService = ServiceLocator.getAdminService();	
    private DaoAdmin daoAdmin;
    private DaoDirecteur daoDirecteur;
    private DaoEnseignant daoEnseignant;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisation des ressources nécessaires, par exemple, l'accès à la base de données ou aux services
        daoAdmin = adminService.getDaoAdmin();
        daoDirecteur = adminService.getDaoDirecteur();
        daoEnseignant = adminService.getDaoEnseignant();
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String username = httpRequest.getParameter("username");
        String password = httpRequest.getParameter("password");

        Admin admin = daoAdmin.selectAdminByUsername(username);
        // Vérifiez les informations d'identification de l'utilisateur
        if (admin != null && admin.authenticate(password)) {
            // Redirigez l'administrateur vers la page d'administration
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin.jsp");
        } else if (checkDirecteur(username, password)) {
            // Redirigez le directeur général vers sa page spécifique
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/directeur.jsp");
            
         // Dans votre filtre d'authentification
        } else if (checkEnseignant(username, password)) {
                // Récupérer l'enseignant depuis la base de données
                Enseignant enseignant = daoEnseignant.getEnseignantByUsername(username);
                
                // Définir l'enseignant dans la session HTTP
                HttpSession session = httpRequest.getSession();
                session.setAttribute("enseignant", enseignant);
                
                // Redirigez l'enseignant vers sa page spécifique
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/enseignant.jsp");    
        } else {
            // Informations d'identification incorrectes, redirigez vers une page d'erreur
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/error.jsp");
        }
    }    

    private boolean checkDirecteur(String username, String password) {
        Directeur directeur = daoDirecteur.getDirecteurByUsername(username);
        return directeur != null && directeur.authenticate(password);
    }

    private boolean checkEnseignant(String username, String password) {
        Enseignant enseignant = daoEnseignant.getEnseignantByUsername(username);
        return enseignant != null && enseignant.authenticate(password);
    }

    @Override
    public void destroy() {
        // Libérez les ressources utilisées par le filtre
    }
}