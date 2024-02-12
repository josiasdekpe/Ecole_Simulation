<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ecole_sim.service.DirecteurServiceImpl, com.ecole_sim.service.DirecteurService" %>
<%@ page import="com.ecole_sim.model.*" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="com.ecole_sim.util.ServiceLocator, java.util.Map, java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Directeur Dashboard</title>
</head>
<body>
    <h1>Directeur Dashboard</h1>
    
    <% 
     // Récupérer l'instance de AdminService à partir de la configuration de l'application
     DirecteurService directeurService = ServiceLocator.getDirecteurService();
    %>
    <!-- Formulaire pour ajouter une matière -->
    <h2>Ajouter une Matière</h2>
    <form action="directeur" method="post">
        <input type="hidden" name="action" value="addMatiere">
        <label for="nomMatiere">Nom:</label><br>
        <input type="text" id="nomMatiere" name="nom"><br><br>
        <input type="submit" value="Ajouter Matière">
    </form>
 <!-- Tableau pour afficher la liste des matières -->
    <h2>Liste des Matières</h2>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Nom</th>
            </tr>
        </thead>
        <tbody>
            <%-- Remplacer les valeurs statiques par les données réelles récupérées depuis la base de données --%>
            <% 
            // Récupérer la liste des directeurs à partir du service
            DaoMatiere daoMatiere = directeurService.getDaoMatiere(); // Vous devez probablement implémenter un mécanisme pour récupérer cette instance
            int countMatieres = 1;
            for (Matiere matiere : daoMatiere.getMatieres()) {
        %>
                <tr>
                    <td><%= countMatieres++ %></td>
                    <td><%= matiere.getNom() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <!-- Formulaire pour ajouter un enseignant -->
    <h2>Ajouter un Enseignant</h2>
    <form action="directeur" method="post">
        <input type="hidden" name="action" value="addEnseignant">
        <label for="enseignantUsername">EnseignantUsername:</label><br>
        <input type="text" id="enseignantUsername" name="enseignantUsername"><br>
        <label for="enseignantPassword">EnseignantPassword:</label><br>
        <input type="text" id="enseignantPassword" name="enseignantPassword"><br><br>
        <input type="submit" value="Ajouter Enseignant">
    </form>


    <!-- Tableau pour afficher la liste des enseignants -->
    <h2>Liste des Enseignants</h2>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Username</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody>
            <%-- Remplacer les valeurs statiques par les données réelles récupérées depuis la base de données --%>
            <% 
            // Récupérer la liste des directeurs à partir du service
            DaoEnseignant daoEnseignant = directeurService.getDaoEnseignant(); // Vous devez probablement implémenter un mécanisme pour récupérer cette instance
            int countEnseignants = 1;
            for (Enseignant enseignant : daoEnseignant.getEnseignants()) {
        %>
                <tr>
                    <td><%= countEnseignants++ %></td>
                    <td><%= enseignant.getUsername() %></td>
                    <td><%= enseignant.getPassword() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    
 <!-- Formulaire pour assigner un enseignant à une matière -->
<h2>Assigner un Enseignant à une Matière</h2>
<form action="directeur" method="post">
    <input type="hidden" name="action" value="assignEnseignantToMatiere">
    
    <!-- Sélection de l'enseignant -->
    <label for="enseignant">Enseignant:</label><br>
    <select id="enseignant" name="enseignantUsername">
        <% 
        for (Enseignant enseignant : daoEnseignant.getEnseignants()) {
        %>
            <option value="<%= enseignant.getUsername() %>"><%= enseignant.getUsername() %></option>
        <% } %>
    </select><br>
    
    <!-- Sélection de la matière -->
    <label for="matiere">Matière:</label><br>
    <select id="matiere" name="matiereId">
        <% 
        for (Matiere matiere : daoMatiere.getMatieres()) {
        %>
            <option value="<%= matiere.getNom() %>"><%= matiere.getNom() %></option>
        <% } %>
    </select><br><br>
    
    <!-- Bouton pour soumettre le formulaire -->
    <input type="submit" value="Assigner Enseignant à Matière">
</form>



<!-- Tableau pour afficher le croisement Directeur-Enseignant -->
<h2>Matrice d'Assignations Matières-Enseignants</h2>
<table border="1">
    <thead>
        <tr>
            <th>No</th>
            <th>Matiere</th>
            <th>Enseignants</th>
        </tr>
    </thead>
    <tbody>
        <%-- Récupérer la liste des matières et afficher les directeurs et les enseignants --%>
        <% 
        // Initialiser le compteur
        int count = 1;
        
        // Parcourir toutes les matières
        for (Matiere matiere : daoMatiere.getMatieres()) {
        %>
            <tr>
                <!-- Afficher le compteur -->
                <td><%= count++ %></td>
                
                <!-- Afficher le nom de la matiere -->
                <td><%= matiere.getNom() %></td>
                
                <td>
                    <%-- Afficher les enseignants disponibles pour cette matière --%>
                    <ul>
                        <% 
                        // Parcourir tous les enseignants
                        for (Enseignant enseignant : daoEnseignant.getEnseignants()) {
                            // Vérifier si l'enseignant enseigne cette matière
                            if (matiere.isEnseignedBy(enseignant)) {
                        %>
                                <li><%= enseignant.getUsername() %></li>
                        <%     }
                        } %>
                    </ul>
                </td>
            </tr>
        <% } %>
    </tbody>
</table>
<!-- Formulaire pour ajouter un créneau -->
<h2>Ajouter un Créneau</h2>
<form action="directeur" method="post">
    <input type="hidden" name="action" value="insertCreneau">
    
    <!-- Champ pour la date -->
    <label for="dateCreneau">Date:</label><br>
    <input type="text" id="dateCreneau" name="date" placeholder="YYYY-MM-DD"><br>
    
    <!-- Sélection de la plageHoraire -->
    <label for="plageHoraire">Plage Horaire:</label><br>
    <select id="plageHoraire" name="plageHoraire">
        <option value="8h-10h">8h-10h</option>
        <option value="10h-12h">10h-12h</option>
        <option value="15h-17h">15h-17h</option>
        <option value="17h-19h">17h-19h</option>
    </select><br>
    
    <!-- Sélection de la matière -->
    <label for="matiere">Nom Matière:</label><br>
    <select id="matiere" name="matiereNom">
        <% 
        for (Matiere matiere : directeurService.getDaoMatiere().getMatieres()) {
        %>
            <option value="<%= matiere.getNom() %>"><%= matiere.getNom() %></option>
        <% } %>
    </select><br>
    
    <!-- Sélection de l'enseignant -->
    <label for="enseignant">Enseignant:</label><br>
    <select id="enseignant" name="enseignantNom">
        <% 
        for (Enseignant enseignant : directeurService.getDaoEnseignant().getEnseignants()) {
        %>
            <option value="<%= enseignant.getUsername() %>"><%= enseignant.getUsername() %></option>
        <% } %>
    </select><br><br>
    
    <!-- Bouton pour soumettre le formulaire -->
    <input type="submit" value="Ajouter Créneau">
</form>


    
<!-- Tableau pour afficher la liste des créneaux -->
<h2>Liste des Créneaux</h2>
<table border="1">
    <thead>
        <tr>
            <th>Id Creneau</th>
            <th>Date</th>
            <th>Plage Horaire</th>
            <th>Matière</th>
            <th>Enseignant</th>
            <th>Actions</th> <!-- Nouvelle colonne pour les actions -->
        </tr>
    </thead>
    <tbody>
        <% 
        DaoCreneau daoCreneau = directeurService.getDaoCreneau(); 
        for (Creneau creneau : daoCreneau.getCreneaux()) {
            Date dateCreneau = creneau.getDate();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String formattedDate = "";
            try {
                formattedDate = sdf.format(dateCreneau);
            } catch (Exception e) {
                formattedDate = "Date non disponible";
                e.printStackTrace();
            }
        %>
        <tr>
            <td><%= creneau.getId() %></td>
            <td><%= formattedDate %></td>
            <td><%= creneau.getPlageHoraire() %></td>
            <td><%= creneau.getMatiere().getNom() %></td>
            <td><%= creneau.getEnseignant().getUsername() %></td>
            <!-- Ajouter une colonne pour les boutons d'édition -->
            <td>
                <!-- Bouton "Modifier Créneau" -->
                <button onclick="showEditForm('<%= creneau.getId() %>')">Modifier Créneau</button>
				<!-- Formulaire caché pour l'édition du créneau -->
				<form id="editForm_<%= creneau.getId() %>" action="directeur" method="post" style="display: ;">
				    <input type="hidden" name="action" value="updateCreneau">
				    <input type="hidden" name="creneauId" value="<%= creneau.getId() %>">
				    Nouvelle date: <input type="text" name="date" placeholder="YYYY-MM-DD" value="<%= formattedDate %>"><br>
				    Nouvelle plage horaire: <input type="text" name="plageHoraire" value="<%= creneau.getPlageHoraire() %>"><br>
				    Nouvelle matière:
				    <select name="matiereNom">
				        <% 
				        for (Matiere matiere : directeurService.getDaoMatiere().getMatieres()) {
				            String selected = (matiere.getNom().equals(creneau.getMatiere().getNom())) ? "selected" : "";
				        %>
				            <option value="<%= matiere.getNom() %>" <%= selected %>><%= matiere.getNom() %></option>
				        <% } %>
				    </select><br>
				    Nouvel enseignant:
				    <select name="enseignantNom">
				        <% 
				        for (Enseignant enseignant : directeurService.getDaoEnseignant().getEnseignants()) {
				            String selected = (enseignant.getUsername().equals(creneau.getEnseignant().getUsername())) ? "selected" : "";
				        %>
				            <option value="<%= enseignant.getUsername() %>" <%= selected %>><%= enseignant.getUsername() %></option>
				        <% } %>
				    </select><br>
				    <input type="submit" value="Enregistrer">
				    <!-- Bouton "Annuler" pour masquer le formulaire -->
				    <button type="button" onclick="hideEditForm('<%= creneau.getId() %>')">Annuler</button>
				</form>


            </td>
        </tr>
        <% } %>
    </tbody>
</table>

<!-- Script JavaScript pour afficher le formulaire d'édition lors du clic sur le bouton "Modifier Créneau" -->
<script>
    function showEditForm(creneauId) {
        // Cacher tous les formulaires d'édition
        var editForms = document.querySelectorAll('form[id^="editForm_"]');
        editForms.forEach(function(form) {
            form.style.display = 'none';
        });
        // Afficher le formulaire d'édition spécifique au créneau sélectionné
        var specificEditForm = document.getElementById('editForm_' + creneauId);
        specificEditForm.style.display = 'block';
    }
    
    // Fonction pour masquer le formulaire d'édition
    function hideEditForm(creneauId) {
        var specificEditForm = document.getElementById('editForm_' + creneauId);
        specificEditForm.style.display = 'none';
    }
</script>

    
	<script>
	// Récupérer le message de confirmation de la requête
	var confirmationMessage = "<%= request.getAttribute("confirmationMessage") %>";
	
	// Vérifier si le message de confirmation est défini et non vide
	if (confirmationMessage && confirmationMessage.trim().length > 5) {
	    // Afficher une pop-up avec le message de confirmation
	    alert(confirmationMessage);
	}
	</script>
</body>
</html>
