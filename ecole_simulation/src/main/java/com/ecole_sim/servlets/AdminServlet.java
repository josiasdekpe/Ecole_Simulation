package com.ecole_sim.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.model.Creneau;
import com.ecole_sim.service.AdminService;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private AdminService adminService; 
    
    public AdminServlet() {
        super();
    }
    
    public AdminServlet(AdminService adminService) {
        super();
        this.adminService = adminService; 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("addDirecteur".equals(action)) {
            addDirecteur(request, response);
        } else if ("addEnseignant".equals(action)) {
            addEnseignant(request, response);
        } else if ("addMatiere".equals(action)) {
            addMatiere(request, response);
        } else if ("addCreneau".equals(action)) {
            addCreneau(request, response);
        } else if ("updateAdminPassword".equals(action)) {
            updateAdminPassword(request, response);
        }
    }

    private void addEnseignant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Enseignant enseignant = new Enseignant(nom, prenom);
        adminService.addEnseignant(enseignant);
        
        // Rediriger vers une page de confirmation par exemple
        response.sendRedirect("confirmation.jsp");
	}

	private void addDirecteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        
        Directeur directeur = new Directeur(nom, prenom);
        adminService.addDirecteur(directeur);
        
        // Rediriger vers une page de confirmation par exemple
        response.sendRedirect("confirmation.jsp");
    }

    private void addMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        
        // Le directeur par défaut
        Directeur directeur = new Directeur("Default", "Directeur");
        
        Matiere matiere = new Matiere(nom, directeur);
        adminService.addMatiere(matiere);
        
        // Rediriger vers une page de confirmation par exemple
        response.sendRedirect("confirmation.jsp");
    }

    private void addCreneau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String nomMatiere = request.getParameter("nomMatiere");
        
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);
        
        // Récupérer le directeur par défaut
        Directeur directeur = new Directeur("Default", "Directeur");
        
        // Créer un créneau avec les paramètres fournis
        Creneau creneau = new Creneau(date, plageHoraire, new Matiere(nomMatiere, directeur), null); // null car nous n'avons pas encore les enseignants
        
        // Ajouter le créneau
        adminService.addCreneau(creneau);
        
        // Rediriger vers une page de confirmation par exemple
        response.sendRedirect("confirmation.jsp");
    }

    private void updateAdminPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String username = request.getParameter("username");
        // Mettre à jour le mot de passe de l'administrateur par défaut
        adminService.changeAdminPassword(username, newPassword);
        
        // Rediriger vers une page de confirmation par exemple
        response.sendRedirect("confirmation.jsp");
    }

    // Méthode pour convertir une chaîne en objet Date
    private Date parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
