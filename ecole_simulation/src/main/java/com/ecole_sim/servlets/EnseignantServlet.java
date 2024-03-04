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
import jakarta.servlet.http.HttpSession;

import com.ecole_sim.model.Creneau;
import com.ecole_sim.model.Enseignant;
import com.ecole_sim.model.Matiere;
import com.ecole_sim.service.EnseignantService;
import com.ecole_sim.util.ServiceLocator;
import com.ecole_sim.util.DaoLocator;

@WebServlet("/enseignant")
public class EnseignantServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private EnseignantService enseignantService = ServiceLocator.getEnseignantService();

    public EnseignantServlet() {
        super();
        this.enseignantService =  ServiceLocator.getEnseignantService();;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("insertCreneau".equals(action)) {
            enseigneMatiere(request, response);
        } else if ("updateCreneau".equals(action)) {
            updateCreneau(request, response);
        } else if ("deleteCreneau".equals(action)) {
            deleteCreneau(request, response);            
        } else if ("peutenseignerMatiere".equals(action)) {
            peutEnseignerMatiere(request, response);
        } else if ("demissionMatiere".equals(action)) {
        	demissionMatiere(request, response);            
        }
    }

    private void enseigneMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String matiereNom = request.getParameter("matiereNom");
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");

        // Récupérer la session HTTP
        HttpSession session = request.getSession();
        // Récupérer l'enseignant à partir de la session
        Enseignant enseignant = (Enseignant) session.getAttribute("enseignant");

        try {
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);

        // Appeler la méthode du service pour enseigner la matière
        enseignantService.enseigneMatiere(enseignant.getUsername(), matiereNom, date, plageHoraire);

        response.sendRedirect(request.getContextPath() + "/enseignant.jsp");
        }catch (ParseException e) {
            // Redirection vers une page d'erreur si la date n'a pas pu être analysée
            response.sendRedirect(request.getContextPath() + "/error.jsp?errorType=date");
        }
    }

    // Méthode pour modifier un créneau
    private void updateCreneau(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Récupérer la session HTTP
        HttpSession session = request.getSession();
        // Récupérer l'enseignant à partir de la session
        Enseignant enseignant = (Enseignant) session.getAttribute("enseignant");
        
        String dateString = request.getParameter("date");
        String plageHoraire = request.getParameter("plageHoraire");
        String matiereNom = request.getParameter("matiereNom");
        int creneauId = Integer.parseInt(request.getParameter("creneauId"));  
        // Récupérer la matière par son nom
        Matiere matiere = DaoLocator.getDaoMatiere().getMatiereByName(matiereNom);
        
        try {
        // Convertir la chaîne en objet Date
        Date date = parseDate(dateString);   
        Creneau creneau = new Creneau(date, plageHoraire, matiere, enseignant); 
        creneau.setId(creneauId);
     
        enseignantService.updateCreneau(creneau);
        // Redirection vers la même page
        response.sendRedirect(request.getContextPath() + "/enseignant.jsp");
        
        }catch (ParseException e) {
            // Redirection vers une page d'erreur si la date n'a pas pu être analysée
            response.sendRedirect(request.getContextPath() + "/error.jsp?errorType=date");
        }
	}

    private void peutEnseignerMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la session HTTP
        HttpSession session = request.getSession();
        // Récupérer l'enseignant à partir de la session
        Enseignant enseignant = (Enseignant) session.getAttribute("enseignant");
        String matiereNom = request.getParameter("matiereId");

        // Appeler la méthode du service pour vérifier si l'enseignant peut enseigner la matière
        enseignantService.peutenseignerMatiere(enseignant.getUsername(), matiereNom);

        response.sendRedirect(request.getContextPath() + "/enseignant.jsp");
    }
    
    private void demissionMatiere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String name = request.getParameter("matiereNom");
        Matiere matiere = DaoLocator.getDaoMatiere().getMatiereByName(name);
        HttpSession session = request.getSession();
        Enseignant enseignant = (Enseignant) session.getAttribute("enseignant");

        if (matiere != null) {
            // Si la matière existe, alors on peut la retirer de l'enseignant
            matiere.removeEnseignant(enseignant);
            response.sendRedirect(request.getContextPath() + "/enseignant.jsp");
        } else {
            // Gérer le cas où la matière n'existe pas
            response.sendRedirect(request.getContextPath() + "/error.jsp?errorType=matiereNotFound");
            return;
        }                     
	}         
   
    private void deleteCreneau(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("creneauId"));
        DaoLocator.getDaoCreneau().deleteCreneau(id);
        
        response.sendRedirect(request.getContextPath() + "/enseignant.jsp");        
	}  

    
    // Méthode pour convertir une chaîne en objet Date
    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.parse(dateString);
    }   
}
