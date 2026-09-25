# 🏦 El Baraka — Analyse des Transactions Bancaires

Application Java permettant de gérer et d’analyser les données bancaires afin de faciliter le suivi des clients, des comptes et des transactions, ainsi que la détection des comportements inhabituels.

## 📋 Description

**El Baraka** est une application développée dans le cadre du **Brief 3 – Sprint 1**.

L’objectif du projet est de proposer une solution permettant aux gestionnaires et analystes financiers de :

* centraliser les informations des clients, comptes et transactions ;
* gérer les comptes bancaires ;
* enregistrer et consulter les transactions ;
* analyser l’activité financière ;
* détecter les transactions suspectes ;
* identifier les comptes inactifs ;
* générer des rapports d’analyse.

## 🎯 Objectifs

L’application permet notamment de :

* gérer les clients ;
* créer et gérer des comptes courants et épargne ;
* enregistrer les transactions ;
* consulter l’historique des opérations ;
* filtrer et regrouper les transactions ;
* calculer des statistiques financières ;
* détecter des anomalies ;
* identifier les comptes inactifs ;
* produire des rapports financiers.

## 🛠️ Technologies utilisées

| Technologie  | Utilisation                          |
| ------------ | ------------------------------------ |
| Java 17      | Langage principal                    |
| JDBC         | Connexion à la base de données       |
| MySQL        | Persistance des données              |
| Maven        | Gestion du projet et des dépendances |
| Git / GitHub | Versionnement du code                |

## 🏗️ Architecture

Le projet suit une architecture en couches :

```text
┌──────────────────────────────┐
│             UI               │
│       Menu interactif        │
└──────────────┬───────────────┘
               │
┌──────────────▼───────────────┐
│          Services            │
│      Logique métier          │
└──────────────┬───────────────┘
               │
┌──────────────▼───────────────┐
│             DAO              │
│        Accès aux données     │
└──────────────┬───────────────┘
               │
┌──────────────▼───────────────┐
│            JDBC              │
└──────────────┬───────────────┘
               │
┌──────────────▼───────────────┐
│            MySQL             │
└──────────────────────────────┘
```

### Structure du projet

```text
El Baraka/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   └── java/
    │       └── org/
    │           └── baraka/
    │               └── App.java
    └── test/
        └── java/
            └── org/
                └── baraka/
                    └── AppTest.java
```

## 💰 Modèle métier

### Client

Un client est représenté par :

* `id`
* `nom`
* `email`

### Compte

La hiérarchie des comptes est basée sur une `sealed class`.

Deux types de comptes sont prévus :

* **CompteCourant**

  * découvert autorisé
* **CompteEpargne**

  * taux d’intérêt

### Transaction

Une transaction contient :

* `id`
* `date`
* `montant`
* `type`
* `lieu`
* `idCompte`

Les types de transactions sont :

* `VERSEMENT`
* `RETRAIT`
* `VIREMENT`

## 🔎 Détection des anomalies

L’application permet de détecter plusieurs comportements inhabituels :

### Montant élevé

Une transaction dépassant un seuil défini peut être considérée comme suspecte.

Exemple :

```text
Montant > 10 000 €
```

### Lieu inhabituel

Une transaction effectuée dans un lieu différent du lieu habituel du client peut générer une alerte.

### Fréquence excessive

Plusieurs transactions effectuées dans un délai très court peuvent être considérées comme suspectes.

### Compte inactif

Un compte sans activité pendant une période définie peut être identifié comme inactif.

## 📊 Analyses et rapports

L’application permet de produire différentes analyses :

* Top 5 des clients par solde ;
* nombre de transactions par type ;
* volume total des transactions ;
* moyenne des transactions ;
* transactions suspectes ;
* comptes inactifs ;
* alertes sur les soldes faibles.

## ☕ Fonctionnalités Java 17

Le projet met en pratique plusieurs fonctionnalités modernes de Java :

* `record`
* `sealed class`
* `switch expressions`
* `var`
* Stream API
* Collectors
* Optional
* Lambda expressions

## 🗄️ Base de données

La persistance des données est assurée avec **MySQL** et **JDBC**.

Relations principales :

```text
Client
   │
   │ 1
   │
   │ N
 Compte
   │
   │ 1
   │
   │ N
Transaction
```

Un client peut posséder plusieurs comptes et un compte peut contenir plusieurs transactions.

## 🚀 Installation et exécution

### Prérequis

Avant de lancer le projet, installer :

* Java JDK 17
* Maven
* MySQL

Vérifier Java :

```bash
java -version
```

Vérifier Maven :

```bash
mvn -version
```

### Cloner le projet

```bash
git clone <URL_DU_REPOSITORY>
```

Puis :

```bash
cd "El Baraka"
```

### Compiler le projet

```bash
mvn clean package
```

### Exécuter

```bash
mvn exec:java
```

ou exécuter la classe principale depuis IntelliJ IDEA.

## 🧪 Tests

Les tests peuvent être exécutés avec :

```bash
mvn test
```

## 📸 Captures d’écran

Ajouter ici les captures d’écran de :

* menu principal ;
* création d’un client ;
* création d’un compte ;
* enregistrement d’une transaction ;
* historique des transactions ;
* analyse des anomalies ;
* rapports.

## 👩‍💻 Projet

**Projet individuel — Brief 3 / Sprint 1**

**Technologies :** Java 17 · JDBC · MySQL · Maven · Git

**Date :** Septembre 2026

---

## 📌 Améliorations possibles

* Export des rapports en CSV ou JSON.
* Ajout d’un système de logs.
* Amélioration des règles de détection des anomalies.
* Ajout d’une interface graphique ou web.
* Ajout de nouvelles statistiques financières.
