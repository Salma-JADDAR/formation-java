import Entity.Client;
import Entity.Compte;
import Entity.Transaction;
import Enums.TypeTransaction;
import Exceptions.CompteNotFoundException;
import Exceptions.MontantInvalideException;
import Exceptions.SoldeInsuffisantException;
import Exceptions.TransactionNotFoundException;
import Exceptions.TransactionSuspecteException;
import Services.ClientService;
import Services.CompteService;
import Services.RapportService;
import Services.TransactionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    static ClientService clientService = new ClientService();
    static CompteService compteService = new CompteService();
    static TransactionService transactionService = new TransactionService();
    static RapportService rapportService = new RapportService();

    public static void main(String[] args) {

        int choix;

        do {
            System.out.println("\n========== EL BARAKA ==========");
            System.out.println("1. Créer un client et ses comptes");
            System.out.println("2. Enregistrer une transaction");
            System.out.println("3. Consulter l'historique d'un compte");
            System.out.println("4. Lancer une analyse");
            System.out.println("5. Voir les alertes");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1:
                    creerClientEtComptes();
                    break;

                case 2:
                    enregistrerTransaction();
                    break;

                case 3:
                    historiqueCompte();
                    break;

                case 4:
                    menuAnalyse();
                    break;

                case 5:
                    menuAlertes();
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);

        scanner.close();
    }

    public static void creerClientEtComptes() {

        System.out.println("\n===== CREER UN CLIENT =====");

        System.out.print("Nom : ");
        String nom = scanner.nextLine();

        System.out.print("Email : ");
        String email = scanner.nextLine();

        System.out.print("Pays habituel : ");
        String payshabitue = scanner.nextLine();

        Client client = new Client(
                0,
                nom,
                email,
                payshabitue
        );

        clientService.ajouter(client);

        Client clientCree = null;

        for (Client c : clientService.findAll()) {
            if (c.email().equals(email)) {
                clientCree = c;
                break;
            }
        }

        if (clientCree == null) {
            System.out.println("Impossible de récupérer le client.");
            return;
        }

        System.out.println("Client créé avec l'id : " + clientCree.id());

        System.out.print("Nombre de comptes à créer : ");
        int nombre = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < nombre; i++) {

            System.out.println("\n===== COMPTE " + (i + 1) + " =====");
            System.out.println("1. Compte courant");
            System.out.println("2. Compte épargne");
            System.out.print("Type : ");

            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Numéro du compte : ");
            String numero = scanner.nextLine();

            System.out.print("Solde : ");
            double solde = scanner.nextDouble();

            if (type == 1) {

                System.out.print("Découvert autorisé : ");
                double decouvert = scanner.nextDouble();
                scanner.nextLine();

                compteService.ajouterCompteCourant(
                        numero,
                        solde,
                        clientCree.id(),
                        decouvert
                );

                System.out.println("Compte courant créé.");

            } else if (type == 2) {

                System.out.print("Taux d'intérêt : ");
                double taux = scanner.nextDouble();
                scanner.nextLine();

                compteService.ajouterCompteEpargne(
                        numero,
                        solde,
                        clientCree.id(),
                        taux
                );

                System.out.println("Compte épargne créé.");

            } else {
                System.out.println("Type invalide.");
            }
        }
    }

    public static void enregistrerTransaction() {

        System.out.println("\n===== ENREGISTRER UNE TRANSACTION =====");

        System.out.print("ID du compte : ");
        int idCompte = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Montant : ");
        double montant = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("1. VERSEMENT");
        System.out.println("2. RETRAIT");
        System.out.println("3. VIREMENT");
        System.out.print("Type : ");

        int choixType = scanner.nextInt();
        scanner.nextLine();

        TypeTransaction type;

        if (choixType == 1) {
            type = TypeTransaction.VERSEMENT;
        } else if (choixType == 2) {
            type = TypeTransaction.RETRAIT;
        } else if (choixType == 3) {
            type = TypeTransaction.VIREMENT;
        } else {
            System.out.println("Type invalide.");
            return;
        }

        System.out.print("Lieu : ");
        String lieu = scanner.nextLine();

        Transaction transaction = new Transaction(
                0,
                LocalDateTime.now(),
                montant,
                type,
                lieu,
                idCompte
        );

        try {

            transactionService.ajouter(transaction);

            System.out.println("Transaction enregistrée.");

        } catch (MontantInvalideException |
                 SoldeInsuffisantException |
                 TransactionSuspecteException e) {

            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void historiqueCompte() {

        System.out.println("\n===== HISTORIQUE DU COMPTE =====");

        System.out.print("ID du compte : ");
        int idCompte = scanner.nextInt();
        scanner.nextLine();

        try {

            List<Transaction> transactions =
                    transactionService.rechercheparcompte(idCompte);

            for (Transaction transaction : transactions) {

                System.out.println(
                        transaction.id() +
                                " | " +
                                transaction.date() +
                                " | " +
                                transaction.montant() +
                                " | " +
                                transaction.type() +
                                " | " +
                                transaction.lieu()
                );
            }

        } catch (TransactionNotFoundException e) {

            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void menuAnalyse() {

        int choix;

        do {

            System.out.println("\n===== ANALYSES =====");
            System.out.println("1. Top 5 clients par solde");
            System.out.println("2. Rapport mensuel");
            System.out.println("3. Comptes inactifs");
            System.out.println("4. Transactions avec montant élevé");
            System.out.println("5. Transactions avec lieu inhabituel");
            System.out.println("6. Fréquence excessive");
            System.out.println("7. Solde maximum");
            System.out.println("8. Solde minimum");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1:
                    afficherTop5();
                    break;

                case 2:
                    rapportMensuel();
                    break;

                case 3:
                    afficherComptesInactifs();
                    break;

                case 4:
                    transactionsMontantEleve();
                    break;

                case 5:
                    transactionsLieuInhabituel();
                    break;

                case 6:
                    frequenceExcessive();
                    break;

                case 7:
                    afficherSoldeMaximum();
                    break;

                case 8:
                    afficherSoldeMinimum();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);
    }

    public static void afficherTop5() {

        System.out.println("\n===== TOP 5 CLIENTS PAR SOLDE =====");

        List<Client> clients = rapportService.top5ClientsParSolde();

        for (Client client : clients) {
            System.out.println(
                    client.id() + " - " +
                            client.nom()
            );
        }
    }

    public static void rapportMensuel() {

        System.out.println("\n===== RAPPORT MENSUEL =====");

        System.out.print("Mois : ");
        int mois = scanner.nextInt();

        System.out.print("Année : ");
        int annee = scanner.nextInt();
        scanner.nextLine();

        rapportService.rapportMensuel(mois, annee);
    }

    public static void afficherComptesInactifs() {

        System.out.println("\n===== COMPTES INACTIFS =====");

        System.out.print("Date limite (yyyy-mm-dd) : ");
        LocalDate dateLimite =
                LocalDate.parse(scanner.nextLine());

        List<Compte> comptes =
                rapportService.comptesInactifs(dateLimite);

        if (comptes.isEmpty()) {
            System.out.println("Aucun compte inactif.");
            return;
        }

        for (Compte compte : comptes) {
            System.out.println(
                    compte.getId() + " - " +
                            compte.getNumero() + " - " +
                            compte.getSolde()
            );
        }
    }

    public static void transactionsMontantEleve() {

        System.out.println("\n===== TRANSACTIONS SUSPECTES =====");

        System.out.print("Seuil : ");
        double seuil = scanner.nextDouble();
        scanner.nextLine();

        List<Transaction> transactions =
                rapportService.transactionsMontantEleve(seuil);

        if (transactions.isEmpty()) {
            System.out.println("Aucune transaction trouvée.");
            return;
        }

        for (Transaction transaction : transactions) {

            System.out.println(
                    transaction.id() +
                            " | " +
                            transaction.montant() +
                            " | " +
                            transaction.type() +
                            " | " +
                            transaction.lieu()
            );
        }
    }

    public static void transactionsLieuInhabituel() {

        System.out.println("\n===== LIEU INHABITUEL =====");

        System.out.print("ID du client : ");
        int idClient = scanner.nextInt();
        scanner.nextLine();

        try {

            List<Transaction> transactions =
                    rapportService.transactionsLieuInhabituel(idClient);

            if (transactions.isEmpty()) {
                System.out.println("Aucune transaction dans un lieu inhabituel.");
                return;
            }

            for (Transaction transaction : transactions) {

                System.out.println(
                        transaction.id() +
                                " | " +
                                transaction.date() +
                                " | " +
                                transaction.montant() +
                                " | " +
                                transaction.lieu()
                );
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }

    public static void frequenceExcessive() {

        System.out.println("\n===== FREQUENCE EXCESSIVE =====");

        System.out.print("ID du compte : ");
        int idCompte = scanner.nextInt();
        scanner.nextLine();

        boolean excessive =
                rapportService.transactionsFrequenceExcessive(idCompte);

        if (excessive) {
            System.out.println("Fréquence excessive détectée.");
        } else {
            System.out.println("Fréquence normale.");
        }
    }

    public static void afficherSoldeMaximum() {

        System.out.println("\n===== SOLDE MAXIMUM =====");

        Optional<Compte> compte =
                compteService.soldeMaximum();

        if (compte.isPresent()) {

            System.out.println(
                    "Compte : " + compte.get().getNumero() +
                            " | Solde : " + compte.get().getSolde()
            );

        } else {
            System.out.println("Aucun compte trouvé.");
        }
    }

    public static void afficherSoldeMinimum() {

        System.out.println("\n===== SOLDE MINIMUM =====");

        Optional<Compte> compte =
                compteService.soldeMinimum();

        if (compte.isPresent()) {

            System.out.println(
                    "Compte : " + compte.get().getNumero() +
                            " | Solde : " + compte.get().getSolde()
            );

        } else {
            System.out.println("Aucun compte trouvé.");
        }
    }

    public static void menuAlertes() {

        int choix;

        do {

            System.out.println("\n===== ALERTES =====");
            System.out.println("1. Comptes avec solde bas");
            System.out.println("2. Comptes inactifs");
            System.out.println("0. Retour");
            System.out.print("Votre choix : ");

            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {

                case 1:
                    alertesSoldeBas();
                    break;

                case 2:
                    afficherComptesInactifs();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Choix invalide.");
            }

        } while (choix != 0);
    }

    public static void alertesSoldeBas() {

        System.out.print("Seuil de solde : ");
        double seuil = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("\n===== COMPTES AVEC SOLDE BAS =====");

        List<Client> clients = clientService.findAll();

        for (Client client : clients) {

            try {

                List<Compte> comptes =
                        compteService.rechercherParClient(client.id());

                for (Compte compte : comptes) {

                    if (compte.getSolde() < seuil) {

                        System.out.println(
                                "Client : " + client.nom() +
                                        " | Compte : " + compte.getNumero() +
                                        " | Solde : " + compte.getSolde()
                        );
                    }

                }

            } catch (CompteNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}