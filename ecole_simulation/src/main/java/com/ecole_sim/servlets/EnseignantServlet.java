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

import com.ecole_sim.service.EnseignantService;

@WebServlet("/enseignant")
public class EnseignantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EnseignantService enseignantService;

    public EnseignantServlet(EnseignantService enseignantService) {
        super();
        this.enseignantService = enseignantService;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("enseigneMatiere".equals(action)) {
            enseigneMatiere(request, response);
        } else if ("updateCreneau".equals(action)) {
            updateCreneau(request, response);
        } else if ("peutenseignerMatiere".equals(action)) {
            peutEnseignerMatiere(request, response);
        }
    }

    private void enseigneMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String enseignantUsername = request.getParameter("enseignantUsername");
        String matiereNom = request.getParameter("matiereNom");
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");

        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);

        // Appeler la méthode du service pour enseigner la matière
        enseignantService.enseigneMatiere(enseignantUsername, matiereNom, date, plageHoraire);

        // Redirection vers une page de confirmation
        response.sendRedirect("confirmation.jsp");
    }

    private void updateCreneau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int creneauId = Integer.parseInt(request.getParameter("creneauId"));

        // Appeler la méthode du service pour mettre à jour le créneau
        enseignantService.updateCreneau(creneauId);

        // Redirection vers une page de confirmation
        response.sendRedirect("confirmation.jsp");
    }

    private void peutEnseignerMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String enseignantUsername = request.getParameter("enseignantUsername");
        String matiereNom = request.getParameter("matiereId");

        // Appeler la méthode du service pour vérifier si l'enseignant peut enseigner la matière
        enseignantService.peutenseignerMatiere(enseignantUsername, matiereNom);

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
