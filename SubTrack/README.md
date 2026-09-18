# SubTrack

## Application de gestion d'abonnements

**SubTrack** est une application Java permettant de gérer des abonnements personnels ou professionnels ainsi que les opérations associées.

Le projet est développé dans le cadre de la formation Java avec une architecture organisée en couches afin de séparer la logique métier, l'accès aux données et l'interface utilisateur.

## Objectifs

* Gérer les abonnements.
* Ajouter, modifier et supprimer un abonnement.
* Consulter les abonnements disponibles.
* Gérer les opérations liées aux paiements.
* Appliquer les règles et validations métier.
* Utiliser JDBC pour communiquer avec la base de données.
* Mettre en pratique la programmation orientée objet et l'architecture en couches.

## Technologies utilisées

* **Java 8**
* **JDBC**
* **MySQL**
* **Maven**
* **IntelliJ IDEA**
* **Git / GitHub**

## Architecture

Le projet suit une architecture en couches :

```text
Interface Console
       ↓
    Service
(Logique métier)
       ↓
      DAO
(Accès aux données)
       ↓
      JDBC
       ↓
     MySQL
```

### Service

La couche Service contient la logique métier de l'application.

Elle permet notamment de :

* Ajouter un abonnement.
* Modifier un abonnement.
* Supprimer un abonnement.
* Consulter les abonnements.
* Effectuer les validations métier.
* Gérer les opérations liées aux paiements.

### DAO

La couche DAO est responsable de l'accès aux données.

Elle utilise **JDBC** pour communiquer avec la base de données MySQL et exécuter les requêtes SQL.

## Fonctionnalités principales

### Gestion des abonnements

* Création d'un abonnement
* Modification d'un abonnement
* Suppression d'un abonnement
* Consultation des abonnements

### Gestion des paiements

* Gestion des opérations de paiement liées aux abonnements
* Vérification des données avant l'enregistrement

## Structure du projet

```text
SubTrack/
│
├── src/
│   └── Main.java
│
├── .gitignore
├── SubTrack.iml
└── README.md
```

## Prérequis

Avant d'exécuter le projet, il faut disposer de :

* JDK 8
* IntelliJ IDEA
* MySQL
* Une base de données configurée pour l'application

Vérifier la version de Java :

```bash
java -version
```

Le projet nécessite **Java 8**.

## Installation

Cloner le repository :

```bash
git clone <URL_DU_REPOSITORY>
```

Accéder au projet :

```bash
cd formation-java/SubTrack
```

Ouvrir le projet avec IntelliJ IDEA et sélectionner **JDK 8** comme SDK du projet.

## Lancement

Depuis IntelliJ IDEA :

1. Ouvrir le projet `SubTrack`.
2. Vérifier que le SDK utilisé est Java 8.
3. Configurer la connexion à MySQL.
4. Exécuter la classe `Main`.

## Objectif pédagogique

Ce projet permet de mettre en pratique plusieurs notions de développement Java :

* Programmation orientée objet
* Java 8
* JDBC
* SQL
* Architecture en couches
* Pattern DAO
* Services et logique métier
* Gestion des exceptions
* Git et GitHub

## Évolution du projet

Le projet sera progressivement enrichi avec de nouvelles fonctionnalités et une meilleure séparation des responsabilités entre les différentes couches de l'application.

## Auteur

**Salma JADDAR**

Projet réalisé dans le cadre de la formation Java.
