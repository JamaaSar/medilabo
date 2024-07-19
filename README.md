# MEDILABO

## Présentation

Ce projet aide les médecins à identifier les patients les plus à risque de diabète. Nous nous concentrons sur les données démographiques des patients et les notes des visites médicales.

## Composants du Projet

Le projet est composé des microservices suivants :

- **Microservices :**
  - **Patient** : Gère les informations de base des patients (nom, âge, etc.).
  - **Note** : Permet d'ajouter et de consulter les notes des médecins après les visites.
  - **Risk** : Évalue le risque de diabète basé sur les données collectées.
  - **Frontend** : Interface utilisateur développée avec Angular 16.
  - **Gateway** : Passerelle API construite avec Spring Boot, centralise les appels entre les microservices.

- **Technologies :**
  - **Langage** : Java 21
  - **Backend** : Spring Boot 3.3
  - **Frontend** : Angular 16
  - **NOTE-SERVICE** : MongoDB 3.3.0
  - **PATIENT-SERVICE** : MySQL 8.3.0
  - **Sécurité** : Spring Security avec JWT pour l'authentification

## Fonctionnalités

1. **Gérer les Données Démographiques :**
   - Ajouter et consulter les informations des patients comme leur nom et leur âge.
   - Ces informations aident à évaluer le risque de diabète, surtout avec l'âge.

2. **Ajouter des Notes de Visite :**
   - Après chaque visite, les médecins peuvent ajouter des notes.
   - Cela aide à suivre les comportements à risque des patients.

## Installation avec Docker

Pour installer et faire fonctionner le projet avec Docker :

1. **Clonez le dépôt :**
   ```bash
   git clone https://github.com/JamaaSar/medilabo
   cd medilabo
   docker-compose up -d


## Green Code 
1. **Clean code :**
 - Effectuer des revues de code pour éliminer les erreurs comme les boucles infinies et les bugs.
 - Écrivez du code clair et bien commenté pour faciliter la maintenance et réduire le besoin de réécriture ou de refactoring fréquents.
2. **Pratiques pour une Énergie Écologique :**
 - Choisir des fournisseurs qui utilisent des sources d'énergie renouvelable et des systèmes de refroidissement écoénergétiques.
 - Optimisez le code pour réduire le temps d'exécution et l'utilisation des ressources, ce qui réduit l'empreinte carbone associée au traitement des données.
3. **Optimisation des Performances**
 - Utilisez des algorithmes efficaces en termes de temps et d'espace. Privilégiez les solutions avec une complexité algorithmique plus faible.
 - Implémentez des mécanismes de cache pour éviter de recalculer des valeurs ou de faire des appels réseau inutiles.
4. **Pratiques pour une Infrastructure Durable**
 - Pour les projets Dockerisés, assurez-vous que les conteneurs sont légers et que les images Docker sont optimisées pour réduire leur taille et le temps de démarrage.

