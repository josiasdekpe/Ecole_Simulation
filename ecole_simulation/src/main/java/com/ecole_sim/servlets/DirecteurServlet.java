package com.ecole_sim.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.DaoCreneau;
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.service.DirecteurService;

@WebServlet("/directeur")
public class DirecteurServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DirecteurService directeurService; 
    
    public DirecteurServlet() {
        super();
    }
    
    public DirecteurServlet(DirecteurService directeurService) {
        super();
        this.directeurService = directeurService; 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("addMatiere".equals(action)) {
            addMatiere(request, response);
        } else if ("addEnseignant".equals(action)) {
            addEnseignant(request, response);
        } else if ("insertCreneau".equals(action)) {
            addCreneau(request, response);
        } else if ("updateCreneau".equals(action)) {
            updateCreneau(request, response);
        } else if ("assignEnseignantToMatiere".equals(action)) {
            assignEnseignantToMatiere(request, response);
        }
    }

	private void addMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        
        // Création de la matière
        Matiere matiere = new Matiere(nom, null);
        
        // Ajout de la matière
        directeurService.addMatiere(matiere);
        
        // Redirection vers une page de confirmation
        response.sendRedirect("confirmation.jsp");
    }

    private void addEnseignant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        
        // Création de l'enseignant
        Enseignant enseignant = new Enseignant(nom, prenom);
        
        // Ajout de l'enseignant
        directeurService.addEnseignant(enseignant);
        
        // Redirection vers une page de confirmation
        response.sendRedirect("confirmation.jsp");
    }

    private void assignEnseignantToMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String enseignantUsername = request.getParameter("enseignantUsername");
        int matiereId = Integer.parseInt(request.getParameter("matiereId"));
        DaoEnseignant daoEnseignant = new DaoEnseignant();
        DaoMatiere daoMatiere = new DaoMatiere();       
        // Récupération de l'enseignant et de la matière
        Enseignant enseignant = daoEnseignant.getEnseignantByUsername(enseignantUsername); 
        Matiere matiere = daoMatiere.getMatiereById(matiereId); 
        
        // Assignation de l'enseignant à la matière
        directeurService.assignEnseignantToMatiere(enseignant, matiere);
        
        // Redirection vers une page de confirmation
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
        
        // Récupérer la matière par son nom
        Matiere matiere = directeur.getMatiereByName(nomMatiere);
        if (matiere == null) {
            // Gérer l'erreur si la matière n'est pas trouvée
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "La matière spécifiée n'a pas été trouvée.");
            return;
        }
        
        // Créer un créneau avec les paramètres fournis
        Creneau creneau = new Creneau(date, plageHoraire, matiere, null); // null car nous n'avons pas encore les enseignants
        
        // Ajouter le créneau
        directeurService.addCreneau(creneau);
        
        // Rediriger vers une page de confirmation par exemple
        response.sendRedirect("confirmation.jsp");
    }
    
    // Méthode pour modifier un créneau
    private void updateCreneau(HttpServletRequest request, HttpServletResponse response) {
        int creneauId = Integer.parseInt(request.getParameter("creneauId"));
        DaoCreneau daoCreneau = new DaoCreneau();
		Creneau creneau = daoCreneau.getCreneauById(creneauId); 
        directeurService.updateCreneau(creneau);
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
