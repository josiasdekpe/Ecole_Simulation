<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.ecole_sim.service.EnseignantServiceImpl, com.ecole_sim.service.EnseignantService" %>
<%@ page import="com.ecole_sim.model.*" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="com.ecole_sim.util.ServiceLocator, java.util.Map, java.util.List" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enseignant Dashboard</title>
</head>
<body>
    <h1>Enseignant Dashboard</h1>
        <!-- Bouton de déconnexion -->
    <form action="login.html" method="get">
        <input type="submit" value="Logout">
    </form>
	    <% 
     // Récupérer l'instance de AdminService à partir de la configuration de l'application
     EnseignantService enseignantService = ServiceLocator.getEnseignantService();
        // Si l'enseignant est null dans la session, définir l'enseignant par défaut
        if (session.getAttribute("enseignant") == null) {
        	Enseignant defaultEnseignant = enseignantService.getDaoEnseignant().getEnseignantByUsername("enseignant");
            session.setAttribute("enseignant", defaultEnseignant);
        }
        
        // Récupération de l'enseignant de la session
        Enseignant enseignant = (Enseignant) session.getAttribute("enseignant");

    %>
    
    <!-- Formulaire pour assigner des matières à l'enseignant -->
<h2>Ajouter à mes Matières </h2>
<form action="enseignant" method="post">
    <input type="hidden" name="action" value="peutenseignerMatiere">
    
    
    <!-- Sélection de la matière -->
    <label for="matiere">Matières:</label><br>
    <select id="matiere" name="matiereId">
        <% 
        for (Matiere matiere : enseignantService.getDaoMatiere().getMatieres()) {
        %>
            <option value="<%= matiere.getNom()%>"><%= matiere.getNom() %></option>
        <% } %>
    </select><br>
    
    <!-- Bouton pour soumettre le formulaire -->
    <input type="submit" value="Ajouter à mes matières">
</form>

<!-- Tableau pour afficher le croisement Matière-Enseignant -->
<h2>Liste de mes matières </h2>
<table border="1">
    <thead>
        <tr>
            <th>No</th>
            <th>Mes matières</th>
            <th>Les cours que je donne</th>
        </tr>
    </thead>
    <tbody>
        <%-- Afficher les matières attribuées à l'enseignant --%>
        <tr>
            <!-- Numéro de ligne -->
            <td>1</td>
            <!-- Nom de l'enseignant -->

            <!-- Liste des matières attribuées à l'enseignant_0 -->
            <td>
                <ul>
                    <% 
                    // Parcourir toutes les matières
                    for (Matiere matiere : enseignantService.getDaoMatiere().getMatieres()) {
                        // Vérifier si la matière est attribuée à l'enseignant
                        if (matiere.isEnseignedBy(enseignant)) {
                    %>
                            <li><%= matiere.getNom() %></li>
                    <% }
                    } %>
                </ul>
            </td>
            <td>                    
            	<ul>
					<% 
                    // Parcourir toutes les matières
                    	for ( Creneau creneau : enseignantService.getDaoCreneau().getCreneauxByEnseignant(enseignant.getUsername())){
                    %>
                            <li><%= creneau.getMatiere().getNom() %></li>
                    <% 
                    } %> 
                   </ul>
             </td>
        </tr>
    </tbody>
</table>
    
    
    <!-- Formulaire pour ajouter un créneau -->
<h2>Ajouter un Créneau</h2>
<form action="enseignant" method="post">
    <input type="hidden" name="action" value="insertCreneau">
    
    <!-- Champ pour la date -->
    <label for="dateCreneau">Date:</label><br>
    <input type="text" id="dateCreneau" name="date" placeholder="dd/MM/yyyy"><br>
    
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
        for (Matiere matiere : enseignantService.getDaoMatiere().getMatieres()) {
        %>
            <option value="<%= matiere.getNom() %>"><%= matiere.getNom() %></option>
        <% } %>
    </select><br>
    
    
    <!-- Bouton pour soumettre le formulaire -->
    <input type="submit" value="Ajouter Créneau">
</form>
    
    
    <h2>Liste des Créneaux * Modifier Créneau</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Id Créneau</th>
                <th>Date</th>
                <th>Plage Horaire</th>
                <th>Matière</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
            // Obtention des créneaux de l'enseignant
            List<Creneau> creneaux = enseignantService.getDaoCreneau().getCreneauxByEnseignant(enseignant.getUsername());

            // Parcours des créneaux
            for (Creneau creneau : creneaux) {
                // Récupération des informations du créneau
                int creneauId = creneau.getId();
                Date dateCreneau = creneau.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String formattedDate = "";
                try {
                    formattedDate = sdf.format(dateCreneau);
                } catch (Exception e) {
                    formattedDate = "Date non disponible";
                    e.printStackTrace();
                }
                String plageHoraire = creneau.getPlageHoraire();
                Matiere matiere = creneau.getMatiere();
                String matiereNom = matiere.getNom();
            %>
            <tr>
                <td><%= creneauId %></td>
                <td><%= formattedDate %></td>
                <td><%= plageHoraire %></td>
                <td><%= matiereNom %></td>
                <td>
                    <!-- Bouton "Modifier Créneau" -->
                    <button onclick="showEditForm('<%= creneauId %>'); this.style.display='none'">Modifier Créneau</button>
                    <!-- Formulaire caché pour l'édition du créneau -->
                    <form id="editForm_<%= creneauId %>" action="enseignant" method="post" style="display: none;">
                        <input type="hidden" name="action" value="updateCreneau">
                        <input type="hidden" name="creneauId" value="<%= creneauId %>">
                        Nouvelle date: <input type="text" name="date" placeholder="YYYY-MM-DD" value="<%= formattedDate %>"><br>
                        Nouvelle plage horaire: <input type="text" name="plageHoraire" value="<%= plageHoraire %>"><br>
                        Nouvelle matière:
                        <select name="matiereNom">
                            <% 
                            // Obtention de la liste des matières
                            List<Matiere> matieres = enseignantService.getDaoMatiere().getMatieres();
                            for (Matiere m : matieres) {
                                // Vérification si la matière du créneau est sélectionnée
                                String selected = (m.getNom().equals(matiereNom)) ? "selected" : "";
                            %>
                                <option value="<%= m.getNom() %>" <%= selected %>><%= m.getNom() %></option>
                            <% } %>
                        </select><br>
                        Nouvel enseignant:
                        <select name="enseignantNom">
                        
                        </select><br>
                        <input type="submit" value="Enregistrer">
                        <button type="button" onclick="hideEditForm('<%= creneauId %>'); document.getElementById('editButton_<%= creneauId %>').style.display='block'">Annuler</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </tbody>
    </table>

        <h1></h1>
        <!-- Bouton de déconnexion -->
    <form action="login.html" method="get">
        <input type="submit" value="Logout">
    </form>
    <!-- Script JavaScript pour afficher le formulaire d'édition lors du clic sur le bouton "Modifier Créneau" -->
    <script>
        function showEditForm(creneauId) {
            var specificEditForm = document.getElementById('editForm_' + creneauId);
            specificEditForm.style.display = 'block';
        }
        function hideEditForm(creneauId) {
            var specificEditForm = document.getElementById('editForm_' + creneauId);
            specificEditForm.style.display = 'none';
        }
    </script>

</body>
</html>
