package Services;

import DAO.ClientDAO;
import DAO.CompteDAO;
import DAO.RapportDAO;
import DAO.TransactionDAO;
import Entity.Client;
import Entity.Compte;
import Entity.Transaction;
import Enums.TypeTransaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RapportService {
    private ClientDAO clientDAO =new ClientDAO();
    private RapportDAO rapportDAO = new RapportDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private CompteDAO compteDAO =new CompteDAO();
    public List<Client> top5ClientsParSolde() {
        return clientDAO.findAll()
                .stream()
                .sorted(Comparator.comparingDouble(
                        (Client client) -> compteDAO.findAll()
                                .stream()
                                .filter(c -> c.getIdClient() == client.id())
                                .mapToDouble(Compte::getSolde)
                                .sum()
                ).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    public void rapportMensuel(int mois, int annee) {

        List<Transaction> transactions = transactionDAO.findAll()
                .stream()
                .filter(t -> t.date().getMonthValue() == mois)
                .filter(t -> t.date().getYear() == annee)
                .toList();

        long versements = transactions.stream()
                .filter(t -> t.type() == TypeTransaction.VERSEMENT)
                .count();

        long retraits = transactions.stream()
                .filter(t -> t.type() == TypeTransaction.RETRAIT)
                .count();

        long virements = transactions.stream()
                .filter(t -> t.type() == TypeTransaction.VIREMENT)
                .count();

        double total = transactions.stream()
                .mapToDouble(Transaction::montant)
                .sum();

        System.out.println("VERSEMENT : " + versements);
        System.out.println("RETRAIT : " + retraits);
        System.out.println("VIREMENT : " + virements);
        System.out.println("Total : " + total);
    }

    public List<Transaction> transactionsMontantEleve(double seuil) {
        return transactionDAO.findAll()
                .stream()
                .filter(t -> t.montant() > seuil)
                .collect(Collectors.toList());
    }

    public List<Transaction> transactionsLieuInhabituel(int idClient) {

        Client client = clientDAO.rechercherByid(idClient);

        return transactionDAO.rechercheparclient(idClient)
                .stream()
                .filter(t -> !t.lieu().equalsIgnoreCase(client.payshabituel()))
                .toList();
    }

    public boolean transactionsFrequenceExcessive(int idCompte) {

        LocalDateTime maintenant = LocalDateTime.now();

        return transactionDAO.findAll()
                .stream()
                .filter(t -> t.idCompte() == idCompte)
                .filter(t -> t.date().isAfter(maintenant.minusMinutes(1)))
                .count() > 3;
    }

    public List<Compte> comptesInactifs(LocalDate dateLimite) {

        return compteDAO.findAll()
                .stream()
                .filter(c -> transactionDAO.findAll()
                        .stream()
                        .noneMatch(t -> t.idCompte() == c.getId() && t.date().toLocalDate().isAfter(dateLimite)))
                .collect(Collectors.toList());
    }






}