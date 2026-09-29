package Services;

import DAO.CompteDAO;
import Entity.Compte;
import Exceptions.CompteNotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class CompteService {

    private CompteDAO compteDAO = new CompteDAO();

    public void ajouterCompteCourant(String numero, Double solde,int idClient, Double decouvertAutorise) {

        compteDAO.ajoutercomptecourant(numero, solde, idClient, decouvertAutorise);
    }

    public void ajouterCompteEpargne(String numero, Double solde, int idClient, Double tauxInteret) {

        compteDAO.ajoutercompteepargne(numero, solde, idClient, tauxInteret);
    }

    public void modifierSolde(int id, Double solde) throws CompteNotFoundException {
        Compte compte = compteDAO.rechercheByid(id);
        if (compte == null) {
            throw new CompteNotFoundException("Compte introuvable");
        }
        compteDAO.modifiersolde(id, solde);
    }

    public void modifierDecouvert(int id, Double decouvertAutorise) throws CompteNotFoundException {
        Compte compte = compteDAO.rechercheByid(id);
        if (compte == null) {
            throw new CompteNotFoundException("Compte introuvable");
        }
        compteDAO.modifierdecouvertautoriser(id, decouvertAutorise);
    }

    public void modifierTauxInteret(int id, Double tauxInteret) throws CompteNotFoundException {
        Compte compte = compteDAO.rechercheByid(id);
        if (compte == null) {
            throw new CompteNotFoundException("Compte introuvable");
        }
        compteDAO.modifiertauxinteret(id, tauxInteret);
    }

    public List<Compte> rechercherParClient(int idClient) throws CompteNotFoundException {
        List<Compte> comptes = compteDAO.recherchebyclient(idClient);
        if (comptes.isEmpty()) {
            throw new CompteNotFoundException("Aucun compte trouvé pour ce client");
        }
        return comptes;
    }

    public Compte rechercherParNumero(String numero) throws CompteNotFoundException {
        Compte compte = compteDAO.recherchebynumero(numero);
        if (compte == null) {
            throw new CompteNotFoundException("Compte introuvable");
        }
        return compte;
    }

    public Optional<Compte>  soldeMaximum()  {
        return compteDAO.findAll()
                .stream()
                .max(Comparator.comparingDouble(Compte::getSolde));
    }

    public Optional<Compte> soldeMinimum() {
        return compteDAO.findAll()
                .stream()
                .min(Comparator.comparingDouble(Compte::getSolde));


    }

//    public Optional<Compte>recerchenumcompte(String num){
//        return compteDAO.findAll()
//                .stream()
//                .filter(c->c.getNumero().equals(num))
//                .findFirst();
//    }



}