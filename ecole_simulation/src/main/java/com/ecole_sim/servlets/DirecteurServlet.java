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
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("directeur.jsp").forward(request, response);
    }

    private void addEnseignant(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String enseignantUsername = request.getParameter("enseignantUsername");
        String enseignantPassword = request.getParameter("enseignantPassword");
        
        // Création de l'enseignant
        Enseignant enseignant = new Enseignant(enseignantUsername, enseignantPassword);
        
        // Ajout de l'enseignant
        directeurService.addEnseignant(enseignant);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("directeur.jsp").forward(request, response);
    }

    private void assignEnseignantToMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String enseignantUsername = request.getParameter("enseignantUsername");
        String matiereNom = request.getParameter("matiereId");      
        // Récupération de l'enseignant et de la matière
        Enseignant enseignant = directeurService.getDaoEnseignant().getEnseignantByUsername(enseignantUsername); 
        Matiere matiere = directeurService.getDaoMatiere().getMatiereByName(matiereNom); 
        
        // Assignation de l'enseignant à la matière
        directeurService.assignEnseignantToMatiere(enseignant, matiere);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("directeur.jsp").forward(request, response);
    }

    
    private void addCreneau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String matiereNom = request.getParameter("matiereNom");  
        String enseignantNom = request.getParameter("enseignantNom");
        
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);
        
        // Récupérer la matière par son nom
        Matiere matiere = directeurService.getDaoMatiere().getMatiereByName(matiereNom);
        Enseignant enseignant = directeurService.getDaoEnseignant().getEnseignantByUsername(enseignantNom);
        // Créer un créneau avec les paramètres fournis
        Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant); // null car nous n'avons pas encore les enseignants
        
        // Ajouter le créneau
        directeurService.addCreneau(creneau);
        
        // Afficher une pop-up de confirmation par exemple
        request.setAttribute("confirmationMessage", "Opération effectuée avec succès !");
        request.getRequestDispatcher("directeur.jsp").forward(request, response);
    }
    
    // Méthode pour modifier un créneau
    private void updateCreneau(HttpServletRequest request, HttpServletResponse response) {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String matiereNom = request.getParameter("matiereNom");  
        String enseignantNom = request.getParameter("enseignantNom");
        
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);
        
        // Récupérer la matière par son nom
        Matiere matiere = directeurService.getDaoMatiere().getMatiereByName(matiereNom);
        Enseignant enseignant = directeurService.getDaoEnseignant().getEnseignantByUsername(enseignantNom);
        int creneauId = Integer.parseInt(request.getParameter("creneauId"));
        Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant); 
        creneau.setId(creneauId);
        
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
