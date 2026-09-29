package Services;

import DAO.CompteDAO;
import DAO.TransactionDAO;
import Entity.Compte;
import Entity.Transaction;
import Enums.TypeTransaction;
import Exceptions.MontantInvalideException;
import Exceptions.SoldeInsuffisantException;
import Exceptions.TransactionNotFoundException;
import Exceptions.TransactionSuspecteException;
import java.util.Map;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {

    private TransactionDAO transactionDAO = new TransactionDAO();
    private CompteDAO compteDAO = new CompteDAO();

    public void ajouter(Transaction transaction) throws MontantInvalideException, SoldeInsuffisantException, TransactionSuspecteException {
        if (transaction.montant() <= 0) {
            throw new MontantInvalideException("Le montant doit être supérieur à 0");
        }

        if (transaction.montant() > 10000) {
            throw new TransactionSuspecteException("Transaction suspecte : montant élevé");
        }

        if (transaction.type() == TypeTransaction.RETRAIT || transaction.type() == TypeTransaction.VIREMENT) {
            Compte compte = compteDAO.rechercheByid(transaction.idCompte());
            if (compte == null) {
                throw new SoldeInsuffisantException("Compte introuvable");
            }

            if (transaction.montant() > compte.getSolde()) {
                throw new SoldeInsuffisantException("Solde insuffisant");
            }
        }

        transactionDAO.ajouterTransaction(transaction);
    }

    public List<Transaction> rechercheparcompte(int idCompte) throws TransactionNotFoundException {

        List<Transaction> transactions = transactionDAO.rechercheparcompte(idCompte);

        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("Aucune transaction trouvée pour ce compte");
        }

        return transactions;
    }


    public List<Transaction> rechercheparclient(int idClient) throws TransactionNotFoundException {
        List<Transaction> transactions = transactionDAO.rechercheparclient(idClient);

        if (transactions.isEmpty()) {
            throw new TransactionNotFoundException("Aucune transaction trouvée pour ce client");
        }

        return transactions;
    }

    public List<Transaction> filtrerParMontant(double montant) {
          return transactionDAO.findAll()
                  .stream()
                  .filter(t->t.montant()==montant)
                  .collect(Collectors.toList());
    }

    public List<Transaction> filtrerParType(TypeTransaction type) {
            return transactionDAO.findAll()
                    .stream()
                    .filter(t->t.type()==type)
                    .collect(Collectors.toList());

    }

    public List<Transaction> filtrerParDate(LocalDateTime date) {
          return transactionDAO.findAll()
                  .stream()
                  .filter(t->t.date()==date)
                  .collect(Collectors.toList());

    }

    public List<Transaction> filtrerParLieu(String lieu) {

          return transactionDAO.findAll()
                  .stream()
                  .filter(t->t.lieu()==lieu)
                  .collect(Collectors.toList());
    }

    public Map<TypeTransaction, List<Transaction>>regrouperParType() {
        return transactionDAO.findAll()
                .stream()
                .collect(Collectors.groupingBy(Transaction::type));
    }

    public Map<TypeTransaction,List<Transaction>> regrouperParDate() {
         return transactionDAO.findAll()
                 .stream()
                 .collect(Collectors.groupingBy(Transaction::type));
    }

    public double totalParCompte(int idCompte) {
          return transactionDAO.findAll()
                  .stream()
                  .filter(t->t.idCompte()==idCompte)
                  .mapToDouble(Transaction::montant)
                  .sum();
    }

    public double moyenneParCompte(int idCompte) {
          return transactionDAO.findAll()
                  .stream()
                  .filter(t->t.idCompte()==idCompte)
                  .mapToDouble(Transaction::montant)
                  .average()
                  .orElse(0.0);
    }

    public double totalParClient(int idClient) {
        double total = 0;

        List<Compte> comptes = compteDAO.recherchebyclient(idClient);
        for (Compte c : comptes) {
            total += transactionDAO.findAll()
                    .stream()
                    .filter(t -> t.idCompte() == c.getId())
                    .mapToDouble(Transaction::montant)
                    .sum();
        }

        return total;
    }

    public double moyenneParClient(int idClient) {

        double total = 0;

        List<Compte> comptes = compteDAO.recherchebyclient(idClient);
        for (Compte c : comptes) {
            total += transactionDAO.findAll()
                    .stream()
                    .filter(t -> t.idCompte() == c.getId())
                    .mapToDouble(Transaction::montant)
                    .sum();
        }

        return total/comptes.size();
    }


}