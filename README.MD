MOVIE Database API
-

I. Description
-
- Cette application est une API REST permettant de gérer 
une base de données de films. Elle est développée avec Spring Boot pour le backend
et utilise MongoDB comme base de données.

II. Fonctionnalités
-
- Récupérer la liste des films
- Rechercher un film par ID
- Ajouter des avis à un film 
- Recupérer les avis d'un film

III. Prérequis
- 
- Java 17+
- Compte MongoDB 

IV. Utilisation
- 
- "/api/v1/movies" : récupérer tous les films
- "/api/v1/movies/{imdbId}" : récupérer un film via son identifiant
- "/api/v1/reviews": récupérer les avis