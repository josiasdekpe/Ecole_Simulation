package com.ecole_sim.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.ecole_sim.model.DaoEnseignant;
import com.ecole_sim.model.DaoMatiere;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.service.EnseignantService;

@WebServlet("/enseignant")
public class EnseignantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private EnseignantService enseignantService; 
    
    public EnseignantServlet() {
        super();
    }
    
    public EnseignantServlet(EnseignantService enseignantService) {
        super();
        this.enseignantService = enseignantService; 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("setDisponibilite".equals(action)) {
            setDisponibilite(request, response);
        } else if ("enseigneMatiere".equals(action)) {
            enseigneMatiere(request, response);
        }
    }

    private void setDisponibilite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enseignantId = Integer.parseInt(request.getParameter("enseignantId"));
        String dateString = request.getParameter("date");
        
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);
        
        // Définir la disponibilité pour l'enseignant
        enseignantService.setDisponibilite(enseignantId, date);
        
        // Redirection vers une page de confirmation
        response.sendRedirect("confirmation.jsp");
    }

    private void enseigneMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int enseignantId = Integer.parseInt(request.getParameter("enseignantId"));
        int matiereId = Integer.parseInt(request.getParameter("matiereId"));
        DaoEnseignant daoEnseignant = new DaoEnseignant();
        DaoMatiere daoMatiere = new DaoMatiere();
        Enseignant enseignant = daoEnseignant.getEnseignantById(enseignantId);
        Matiere matiere = daoMatiere.getMatiereById(matiereId);
        // Associer l'enseignant à la matière
        enseignantService.enseigneMatiere(enseignant, matiere);
        
        // Redirection vers une page de confirmation
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
