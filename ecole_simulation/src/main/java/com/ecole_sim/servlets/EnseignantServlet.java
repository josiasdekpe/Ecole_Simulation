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
import com.ecole_sim.model.Matiere;
import com.ecole_sim.service.EnseignantService;
import com.ecole_sim.util.ServiceLocator;

@WebServlet("/enseignant")
public class EnseignantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EnseignantService enseignantService = ServiceLocator.getEnseignantService();

    public EnseignantServlet(EnseignantService enseignantService) {
        super();
        this.enseignantService =  ServiceLocator.getEnseignantService();;
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

    // Méthode pour modifier un créneau
    private void updateCreneau(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String matiereNom = request.getParameter("matiereNom");
        int creneauId = Integer.parseInt(request.getParameter("creneauId"));        
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);
        
        // Récupérer la matière par son nom
        Matiere matiere = enseignantService.getDaoMatiere().getMatiereByName(matiereNom);

        Creneau creneau = new Creneau(date, plageHoraire, matiere); 
        creneau.setId(creneauId);
        
        enseignantService.updateCreneau(creneau);
        // Redirection vers la même page
        response.sendRedirect(request.getContextPath() + "/enseignant.jsp");

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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        try {
            return formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
