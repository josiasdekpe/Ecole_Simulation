<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Erreur</title>
</head>
<body>
    <h1>Erreur</h1>
    <%
        // Récupération de la nature de l'erreur à partir des attributs de la requête
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
    %>
        <p><strong>Nature de l'erreur :</strong> <%= errorMessage %></p>
    <% } else { %>
        <p>Une erreur s'est produite lors du traitement de votre demande.</p>
    <% } %>
    <p>Veuillez revenir à la <a href="login.html">page de connexion</a> et réessayer.</p>
</body>
</html>
