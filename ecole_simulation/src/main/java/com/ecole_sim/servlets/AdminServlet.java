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
import com.ecole_sim.util.ServiceLocator;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
//    private AdminService adminService; 
    private AdminService adminService = ServiceLocator.getAdminService();
    
    public AdminServlet() {
        super();
        this.adminService = ServiceLocator.getAdminService();
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Enseignant enseignant = new Enseignant(username, password);
        adminService.addEnseignant(enseignant);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	private void addDirecteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Directeur directeur = new Directeur(username, password);
        adminService.addDirecteur(directeur);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void addMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        
        Matiere matiere = new Matiere(nom);
        adminService.addMatiere(matiere);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void addCreneau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String nomMatiere = request.getParameter("nomMatiere");
        
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);
        
        // Créer un créneau avec les paramètres fournis
        Creneau creneau = new Creneau(date, plageHoraire, new Matiere(nomMatiere), null); // null car nous n'avons pas encore les enseignants
        
        // Ajouter le créneau
        adminService.addCreneau(creneau);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    private void updateAdminPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        // Mettre à jour le mot de passe de l'administrateur par défaut
        adminService.changeAdminPassword("admin", newPassword);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
    }

    // Méthode pour convertir une chaîne en objet Date
    private Date parseDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
