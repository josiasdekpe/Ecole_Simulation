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
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.service.DirecteurService;
import com.ecole_sim.util.ServiceLocator;
import com.ecole_sim.util.DaoLocator;

@WebServlet("/directeur")
public class DirecteurServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DirecteurService directeurService = ServiceLocator.getDirecteurService(); 
    
    public DirecteurServlet() {
        super();
        this.directeurService = ServiceLocator.getDirecteurService();
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
        Matiere matiere = new Matiere(nom);
        
        // Ajout de la matière
        directeurService.addMatiere(matiere);
        
        response.sendRedirect(request.getContextPath() + "/directeur.jsp");
    }

    private void addEnseignant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enseignantUsername = request.getParameter("enseignantUsername");
        String enseignantPassword = request.getParameter("enseignantPassword");
        
        // Création de l'enseignant
        Enseignant enseignant = new Enseignant(enseignantUsername, enseignantPassword);
        
        // Ajout de l'enseignant
        directeurService.addEnseignant(enseignant);
        
        response.sendRedirect(request.getContextPath() + "/directeur.jsp");
    }

    private void assignEnseignantToMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String enseignantUsername = request.getParameter("enseignantUsername");
        String matiereNom = request.getParameter("matiereId");      
        // Récupération de l'enseignant et de la matière
        Enseignant enseignant = DaoLocator.getDaoEnseignant().getEnseignantByUsername(enseignantUsername); 
        Matiere matiere = DaoLocator.getDaoMatiere().getMatiereByName(matiereNom); 
        
        // Assignation de l'enseignant à la matière
        directeurService.assignEnseignantToMatiere(enseignant, matiere);
        
        // Afficher une pop-up de confirmation par exemple
        response.sendRedirect(request.getContextPath() + "/directeur.jsp");
    }

    
    private void addCreneau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String matiereNom = request.getParameter("matiereNom");  
        String enseignantNom = request.getParameter("enseignantNom");
        
        // Récupérer la matière par son nom
        Matiere matiere = DaoLocator.getDaoMatiere().getMatiereByName(matiereNom);
        Enseignant enseignant = DaoLocator.getDaoEnseignant().getEnseignantByUsername(enseignantNom);
        
        try {
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);
        

        // Créer un créneau avec les paramètres fournis
        Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant); // null car nous n'avons pas encore les enseignants
        
        // Ajouter le créneau
        directeurService.addCreneau(creneau);
        response.sendRedirect(request.getContextPath() + "/directeur.jsp");
        
        }catch (ParseException e) {
            // Redirection vers une page d'erreur si la date n'a pas pu être analysée
            response.sendRedirect(request.getContextPath() + "/error.jsp?errorType=date");
        }
    }
    
    // Méthode pour modifier un créneau
    private void updateCreneau(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String matiereNom = request.getParameter("matiereNom");  
        String enseignantNom = request.getParameter("enseignantNom");
        
        // Récupérer la matière par son nom
        Matiere matiere = DaoLocator.getDaoMatiere().getMatiereByName(matiereNom);
        Enseignant enseignant = DaoLocator.getDaoEnseignant().getEnseignantByUsername(enseignantNom);
        int creneauId = Integer.parseInt(request.getParameter("creneauId"));
        
        try {
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);

        Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant); 
        creneau.setId(creneauId);
        
        directeurService.updateCreneau(creneau);
        // Redirection vers la même page
        response.sendRedirect(request.getContextPath() + "/directeur.jsp");

        }catch (ParseException e) {
            // Redirection vers une page d'erreur si la date n'a pas pu être analysée
            response.sendRedirect(request.getContextPath() + "/error.jsp?errorType=date");
        }
	}

    // Méthode pour convertir une chaîne en objet Date
    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.parse(dateString);
    }   
    
    
}
