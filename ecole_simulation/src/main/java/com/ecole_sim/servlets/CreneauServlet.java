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
import com.ecole_sim.model.Directeur;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.service.DirecteurService;

@WebServlet("/creneau")
public class CreneauServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private DirecteurService directeurService; 
    
    public CreneauServlet() {
        super();
    }
    
    public CreneauServlet(DirecteurService directeurService) {
        super();
        this.directeurService = directeurService; 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        directeurService.updateCreneau(creneau);
        
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
