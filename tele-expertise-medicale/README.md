# Système de Télé-Expertise Médicale

## Description

Le **Système de Télé-Expertise Médicale** est une application web développée avec **Jakarta EE** permettant de faciliter la coordination entre les infirmiers, les médecins généralistes et les médecins spécialistes.

L'application permet de gérer le parcours du patient, les consultations médicales, les signes vitaux, les demandes de télé-expertise, les créneaux des spécialistes ainsi que les actes médicaux et leurs coûts.

L'objectif est de proposer une solution structurée permettant de faciliter l'échange d'informations médicales et d'améliorer la coordination entre les différents professionnels de santé.

---

## Objectifs

* Gérer les patients et leurs informations médicales.
* Enregistrer les signes vitaux des patients.
* Gérer les consultations médicales.
* Permettre aux médecins généralistes de demander une télé-expertise.
* Permettre aux spécialistes de gérer leurs demandes d'expertise.
* Gérer les créneaux disponibles des spécialistes.
* Suivre les avis et recommandations des spécialistes.
* Gérer les actes médicaux réalisés.
* Calculer le coût total d'une prise en charge.
* Gérer l'authentification et les rôles des utilisateurs.

---

## Fonctionnalités

### Infirmier

* Inscription et recherche d'un patient.
* Consultation des informations du patient.
* Enregistrement des signes vitaux.
* Création du dossier médical.
* Ajout du patient à la file d'attente.
* Consultation de la liste des patients enregistrés.

### Médecin généraliste

* Consultation d'un patient.
* Saisie du motif et des observations.
* Prise en charge directe du patient.
* Création d'une demande de télé-expertise.
* Sélection d'une spécialité.
* Recherche et filtrage des spécialistes.
* Consultation des tarifs des spécialistes.
* Sélection d'un créneau disponible.
* Définition de la priorité de la demande.
* Ajout de la question médicale et des données nécessaires.
* Suivi du statut de la consultation.

### Médecin spécialiste

* Gestion du profil professionnel.
* Gestion de la spécialité.
* Définition du tarif de télé-expertise.
* Gestion des créneaux disponibles.
* Consultation des demandes d'expertise.
* Filtrage des demandes par statut et priorité.
* Saisie de l'avis médical.
* Ajout des recommandations.
* Finalisation d'une demande d'expertise.

### Gestion des actes médicaux

L'application permet de gérer différents actes médicaux :

* Radiographie
* Échographie
* IRM
* ECG
* Laser dermatologique
* Examen du fond d'œil
* Analyse de sang
* Analyse d'urine

---

## Architecture

Le projet adopte une architecture **MVC + Layered Architecture** afin de séparer clairement les responsabilités.

```text
                         Navigateur
                             │
                             ▼
                       JSP / JSTL
                             │
                             ▼
                         Servlet
                             │
                             ▼
                          Service
                             │
                             ▼
                    Repository / DAO
                             │
                             ▼
                     JPA / Hibernate
                             │
                             ▼
                           MySQL
```

### Organisation des couches

* **Presentation** : JSP / JSTL
* **Controller** : Servlets
* **Business** : Services
* **Persistence** : Repository / JPA
* **Database** : MySQL
* **Security** : Filters, sessions, BCrypt et CSRF
* **Dependency Injection** : CDI

---

## Structure du projet

```text
tele-expertise-medicale/
│
├── pom.xml
├── README.md
│
├── src/
│   └── main/
│       │
│       ├── java/
│       │   └── org/
│       │       └── telexpertise/
│       │           │
│       │           ├── entity/
│       │           │   ├── User.java
│       │           │   ├── Patient.java
│       │           │   ├── DossierMedical.java
│       │           │   ├── SigneVital.java
│       │           │   ├── Consultation.java
│       │           │   ├── Specialite.java
│       │           │   ├── Specialiste.java
│       │           │   ├── Creneau.java
│       │           │   ├── DemandeExpertise.java
│       │           │   ├── ActeMedical.java
│       │           │   └── Prescription.java
│       │           │
│       │           ├── repository/
│       │           │
│       │           ├── service/
│       │           │
│       │           ├── servlet/
│       │           │
│       │           ├── filter/
│       │           │
│       │           ├── dto/
│       │           │
│       │           ├── exception/
│       │           │
│       │           ├── enums/
│       │           │
│       │           └── util/
│       │
│       ├── resources/
│       │   └── META-INF/
│       │       └── persistence.xml
│       │
│       └── webapp/
│           ├── css/
│           ├── js/
│           ├── assets/
│           ├── index.jsp
│           │
│           └── WEB-INF/
│               ├── web.xml
│               └── views/
│                   ├── infirmier/
│                   ├── generaliste/
│                   └── specialiste/
│
└── ...
```

---

## Modèle de données

Les principales entités du système sont :

```text
User
 │
 └── Specialiste
       │
       ├── Specialite
       └── Creneau

Patient
 │
 ├── DossierMedical
 ├── SigneVital
 └── Consultation
       │
       ├── Prescription
       ├── ActeMedical
       └── DemandeExpertise
              │
              ├── Specialiste
              └── Creneau
```

---

## Technologies utilisées

### Backend

* Java 17
* Jakarta EE 10
* Servlets
* JSP
* JSTL
* CDI
* JPA
* Hibernate

### Frontend

* HTML5
* CSS3
* JavaScript
* JSP / JSTL

### Base de données

* MySQL

### Build

* Maven

### Serveur

* Apache Tomcat / serveur compatible Jakarta EE

### Tests

* JUnit
* Mockito

### Gestion de versions

* Git
* GitHub

---

## Java et programmation fonctionnelle

Le projet utilise également les fonctionnalités modernes de Java pour certaines opérations de traitement des données.

### Stream API

Utilisation de `Stream API` pour :

* Filtrer les patients par date d'enregistrement.
* Filtrer les spécialistes par spécialité.
* Filtrer les spécialistes par tarif.
* Trier les spécialistes par tarif.
* Filtrer les demandes d'expertise par statut.
* Filtrer les demandes par priorité.

### Lambda

Les expressions lambda sont utilisées notamment pour les traitements de collections et le calcul des coûts.

### Calcul du coût

Le coût total d'une prise en charge peut être calculé à partir de :

```text
Coût total =
Coût consultation
+ Coût télé-expertise
+ Coût des actes médicaux
```

---

## Sécurité

Le système prévoit plusieurs mécanismes de sécurité :

* Authentification des utilisateurs.
* Gestion des rôles.
* Gestion des sessions.
* Hashage des mots de passe avec BCrypt.
* Protection contre les attaques CSRF.
* Contrôle d'accès aux fonctionnalités selon le rôle.

Les principaux rôles sont :

```text
GENERALISTE
SPECIALISTE
INFIRMIER
```

---

## Gestion des créneaux

Les spécialistes disposent de créneaux fixes de **30 minutes**.

Exemple :

```text
09:00 - 09:30  → Disponible
09:30 - 10:00  → Disponible
10:00 - 10:30  → Disponible
10:30 - 11:00  → Indisponible
11:00 - 11:30  → Disponible
11:30 - 12:00  → Disponible
```

Un créneau peut évoluer selon son état :

```text
DISPONIBLE
     │
     ▼
  RESERVE
     │
     ▼
  ARCHIVE
```

En cas d'annulation, un créneau réservé peut redevenir disponible selon les règles métier.

---

## Installation

### Prérequis

Avant de lancer le projet, installer :

* JDK 17
* Maven
* MySQL
* Un serveur compatible Jakarta EE
* IntelliJ IDEA recommandé
* Git

### Vérifier Java

```bash
java -version
```

### Vérifier Maven

```bash
mvn -version
```

---

## Configuration de la base de données

Créer une base de données MySQL :

```sql
CREATE DATABASE tele_expertise;
```

La configuration de la connexion à la base de données sera définie dans la configuration JPA et du serveur d'application.

---

## Lancement du projet

Cloner le projet :

```bash
git clone <repository-url>
```

Accéder au projet :

```bash
cd tele-expertise-medicale
```

Compiler le projet :

```bash
mvn clean package
```

Le fichier WAR généré sera disponible dans :

```text
target/
```

---

## Tests

Lancer les tests avec :

```bash
mvn test
```

---

## Évolution du projet

Le développement du projet est réalisé progressivement selon les différentes fonctionnalités métier :

```text
1. Architecture du projet
2. Configuration Maven et Jakarta EE
3. Configuration JPA / Hibernate
4. Configuration MySQL
5. Modélisation des entités
6. Repositories
7. Services métier
8. Servlets
9. JSP / JSTL
10. Authentification et autorisation
11. Gestion des consultations
12. Télé-expertise
13. Gestion des créneaux
14. Gestion des actes médicaux
15. Tests
16. Déploiement
```

---

## Auteur

**Salma JADDAR**

Projet réalisé dans le cadre de la formation **YouCode — Spécialisation Java**.
