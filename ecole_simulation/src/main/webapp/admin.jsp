<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ecole_sim.service.AdminServiceImpl, com.ecole_sim.service.AdminService" %>
<%@ page import="com.ecole_sim.model.*" %>
<%@ page import="java.text.SimpleDateFormat, java.util.Date" %>
<%@ page import="com.ecole_sim.util.ServiceLocator, java.util.Map, java.util.List" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
		.popup {
		    position: fixed; /* La popup reste fixe par rapport à la fenêtre du navigateur */
		    top: 50%; /* Centrage vertical */
		    left: 50%; /* Centrage horizontal */
		    transform: translate(-50%, -50%); /* Centrage précis */
		    background-color: white;
		    border: 1px solid black;
		    padding: 20px;
		    z-index: 9999; /* Assure que la popup est au-dessus de tous les autres éléments */
		}
    </style>
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Admin Dashboard</h1>
        <!-- Formulaire pour ajouter un directeur -->
    <h2>Ajouter un Directeur</h2>
    <form action="admin" method="post">
        <input type="hidden" name="action" value="addDirecteur">
        <label for="username">UsernameDirecteur:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">PasswordDirecteur:</label><br>
        <input type="text" id="password" name="password"><br><br>
        <input type="submit" value="Ajouter Directeur">
    </form>
    <!-- Tableau pour afficher la liste des directeurs -->
    <h2>Liste des Directeurs</h2>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Username</th>
                <th>Password</th>
            </tr>
        </thead>
        <tbody>
            <% 
            // Récupérer l'instance de AdminService à partir de la configuration de l'application
            AdminService adminService = ServiceLocator.getAdminService();
            // Récupérer la liste des directeurs à partir du service
            DaoDirecteur daoDirecteur = adminService.getDaoDirecteur(); // Vous devez probablement implémenter un mécanisme pour récupérer cette instance
            int countDirecteurs = 1;
            for (Directeur directeur : daoDirecteur.getDirecteurs()) {
        %>
            <tr>
                <td><%= countDirecteurs++ %></td>
                <td><%= directeur.getUsername() %></td>
                <td><%= directeur.getPassword() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>

	    <!-- Formulaire pour ajouter un enseignant -->
    <h2>Ajouter un Enseignant</h2>
    <form action="admin" method="post">
        <input type="hidden" name="action" value="addEnseignant">
        <label for="username">UsernameEnseignant:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">PasswordEnseignant:</label><br>
        <input type="text" id="password" name="password"><br><br>
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
            DaoEnseignant daoEnseignant = adminService.getDaoEnseignant(); // Vous devez probablement implémenter un mécanisme pour récupérer cette instance
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
	
    <!-- Formulaire pour ajouter une matière -->
    <h2>Ajouter une Matière</h2>
    <form action="admin" method="post">
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
            DaoMatiere daoMatiere = adminService.getDaoMatiere(); // Vous devez probablement implémenter un mécanisme pour récupérer cette instance
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
<!-- Formulaire pour ajouter un créneau -->
<h2>Ajouter un Créneau</h2>
<form action="admin" method="post">
    <input type="hidden" name="action" value="addCreneau">
    <label for="dateCreneau">Date:</label><br>
    <input type="text" id="dateCreneau" name="date" placeholder="YYYY-MM-DD"><br>
    <label for="plageHoraire">Plage Horaire:</label><br>
    <select id="plageHoraire" name="plageHoraire">
        <option value="8h-10h">8h-10h</option>
        <option value="10h-12h">10h-12h</option>
        <option value="15h-17h">15h-17h</option>
        <option value="17h-19h">17h-19h</option>
    </select><br>
    <label for="nomMatiereCreneau">Nom Matière:</label><br>
    <select id="nomMatiereCreneau" name="nomMatiere">
        <!-- Afficher les options avec les matières actuelles -->
        <% 
            // Récupérer la liste des matières à partir du service
            List<Matiere> matieres = adminService.getDaoMatiere().getMatieres();
            for (Matiere matiere : matieres) {
        %>
        <option value="<%= matiere.getNom() %>"><%= matiere.getNom() %></option>
        <% } %>
    </select><br><br>
    <input type="submit" value="Ajouter Créneau">
</form>

    <!-- Tableau pour afficher la liste des créneaux -->
    <h2>Liste des Créneaux</h2>
    <table border="1">
        <thead>
            <tr>
                <th>No</th>
                <th>Date</th>
                <th>Plage Horaire</th>
                <th>Matière</th>
            </tr>
        </thead>
        <tbody>
            <%-- Remplacer les valeurs statiques par les données réelles récupérées depuis la base de données --%>
            <% 
            int countCreneaux = 1;
            DaoCreneau daoCreneau = adminService.getDaoCreneau(); // Vous devez probablement implémenter un mécanisme pour récupérer cette instance
            for (Creneau creneau : daoCreneau.getCreneaux()) {
                Date dateCreneau = creneau.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                // Formater la date en utilisant le formateur créé
                // Variable pour stocker la date formatée
				String formattedDate = "";
				
				// Tenter de formater la date
				try {
				    // Formater la date en utilisant le formateur créé
				    formattedDate = sdf.format(dateCreneau);
				} catch (Exception e) {
				    // En cas d'erreur, afficher un message d'erreur ou effectuer une action de gestion des erreurs
				    formattedDate = "Date non disponible";
				    // Vous pouvez également enregistrer l'erreur dans un fichier de journalisation ou afficher un message à l'utilisateur
				    e.printStackTrace(); // Ceci affiche l'erreur dans la console du serveur pour le débogage
				}
            %>
                <tr>
                    <td><%= countCreneaux++ %></td>
                    <td><%= creneau.getDate() %></td>
                    <td><%= creneau.getPlageHoraire() %></td>
                    <td><%= creneau.getMatiere().getNom() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>


    <!-- Formulaire pour modifier le mot de passe de l'administrateur -->
    <h2>Modifier le Mot de Passe de l'Administrateur</h2>
<form action="admin" method="post">
    <input type="hidden" name="action" value="updateAdminPassword">
    <label for="username">Nom d'utilisateur:</label><br>
    <!-- Utiliser un span pour afficher l'actuel nom d'utilisateur -->
    <span id="username"><%= adminService.getDaoAdmin().selectAdminByUsername("admin").getUsername() %></span><br>
    <label for="currentPassword">Mot de Passe Actuel:</label><br>
    <!-- Utiliser un span pour afficher l'actuel mot de passe de l'administrateur -->
    <span id="currentPassword"><%= adminService.getDaoAdmin().selectAdminByUsername("admin").getPassword() %></span><br><br>
    <label for="newPassword">Nouveau Mot de Passe:</label><br>
    <input type="password" id="newPassword" name="newPassword"><br><br>
    <input type="submit" value="Modifier le Mot de Passe">
</form>

    <!-- Bouton de déconnexion -->
    <form action="login.html" method="get">
        <input type="submit" value="Logout">
    </form>

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
