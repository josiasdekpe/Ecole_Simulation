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
import com.ecole_sim.util.DaoLocator;
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
            try {
				addCreneau(request, response);
			} catch (ServletException | IOException | ParseException e) {
				e.printStackTrace();
			}
        } else if ("updateAdminPassword".equals(action)) {
            updateAdminPassword(request, response);
        }
    }

    private void addEnseignant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Enseignant enseignant = new Enseignant(username, password);
        adminService.addEnseignant(enseignant);
        
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("admin.jsp").forward(request, response);
	}

	private void addDirecteur(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        Directeur directeur = new Directeur(username, password);
        adminService.addDirecteur(directeur);
        
        response.sendRedirect(request.getContextPath() + "/admin.jsp");
    }

    private void addMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        
        Matiere matiere = new Matiere(nom);
        adminService.addMatiere(matiere);
        
        response.sendRedirect(request.getContextPath() + "/admin.jsp");
    }

    private void addCreneau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String nomMatiere = request.getParameter("nomMatiere");
        String enseignantUsername = request.getParameter("enseignantNom");
        // Convertir la chaîne en objet Date
        
        Matiere matiere = DaoLocator.getDaoMatiere().getMatiereByName(nomMatiere);
        
        Enseignant enseignant = DaoLocator.getDaoEnseignant().getEnseignantByUsername(enseignantUsername);        
        // Vérifier si l'enseignant, la matière et le créneau existent
        
        if (enseignant != null && matiere != null) {
            // Vérifier si l'enseignant est déjà affecté à la matière
            if (!matiere.isEnseignedBy(enseignant)) {
                // Ajouter l'enseignant à la liste des enseignants affectés à la matière
                matiere.addEnseignant(enseignant);
            }
            try {
            	Date date = parseDate(dateString);
                Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant);
                // Ajouter le créneau à la liste des créneaux 
                DaoLocator.getDaoCreneau().insertCreneau(creneau);
                response.sendRedirect(request.getContextPath() + "/admin.jsp");
            }catch (ParseException e) {
                // Redirection vers une page d'erreur si la date n'a pas pu être analysée
                response.sendRedirect(request.getContextPath() + "/error.jsp?errorType=date");
            }
        }
    }

    private void updateAdminPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        // Mettre à jour le mot de passe de l'administrateur par défaut
        adminService.changeAdminPassword("admin", newPassword);
        
        response.sendRedirect(request.getContextPath() + "/admin.jsp");
    }

 // Méthode pour convertir une chaîne en objet Date
    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.parse(dateString);
    }   
}

