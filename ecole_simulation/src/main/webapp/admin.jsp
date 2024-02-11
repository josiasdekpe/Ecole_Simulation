<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.ecole_sim.service.AdminServiceImpl, com.ecole_sim.service.AdminService" %>
<%@ page import="com.ecole_sim.model.*" %>
<%@ page import="com.ecole_sim.util.ServiceLocator, java.util.Map" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard</title>
</head>
<body>
    <h1>Admin Dashboard</h1>
    
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

    <!-- Lien pour ajouter un directeur -->
    <a href="ajoutDirecteur.jsp">Ajouter un Directeur</a>

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

    <!-- Lien pour ajouter un enseignant -->
    <a href="ajoutEnseignant.jsp">Ajouter un Enseignant</a>

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

    <!-- Lien pour ajouter une matière -->
    <a href="ajoutMatiere.jsp">Ajouter une Matière</a>

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

    <!-- Lien pour ajouter un créneau -->
    <a href="ajoutCreneau.jsp">Ajouter un Créneau</a>

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

</body>
</html>
