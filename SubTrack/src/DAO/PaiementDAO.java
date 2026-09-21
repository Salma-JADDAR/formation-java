package DAO;

import Entity.Abonnement;
import Entity.Paiement;
import Enums.StatutPaiement;
import Exceptions.PaiementAlreadyExistsException;
import Exceptions.PaiementNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

public class PaiementDAO {
    private Map<String, Paiement>paiments;

    public PaiementDAO(){
        paiments=new HashMap<>();
    }

    public void create(Paiement p)throws PaiementAlreadyExistsException {
        if(!paiments.containsKey(p.getIdPaiement())){
            paiments.put(p.getIdPaiement(),p);
        }else{
            throw new PaiementAlreadyExistsException("le paiment existe deja");
        }
    }

    public Optional<Paiement>findById(String id){
        return Optional.ofNullable(paiments.get(id));
    }

    public void update(Paiement p)throws PaiementNotFoundException {
        if(paiments.containsKey(p.getIdPaiement())){
            paiments.put(p.getIdPaiement(),p);
        }else{
            throw new PaiementNotFoundException("le paiment n'existe pas");
        }
    }

    public void delete(Paiement p)throws PaiementNotFoundException{
        if(paiments.containsKey(p.getIdPaiement())){
            paiments.remove(p.getIdPaiement());
        }else{
            throw new PaiementNotFoundException("le paiment n'existe pas");
        }
    }

    public List<Paiement>findAll(){
        return new ArrayList<>(paiments.values());
    }

    public List<Paiement>findByAbonnemet(Abonnement a){
        return paiments.values()
                .stream()
                .filter(p->p.getIdAbonnement().equals(a.getId()))
                .collect(Collectors.toList());
    }

    public List<Paiement>findUnpaidByAbonnement(Abonnement a){
        return paiments.values()
                .stream()
                .filter(p->p.getIdAbonnement().equals(a.getId()))
                .filter(p->p.getStatut()== StatutPaiement.NON_PAYE)
                .collect(Collectors.toList());
    }


    public List<Paiement>findLastPayments(){
        return paiments.values()
                .stream()
                .filter(p->p.getDatePaiement()!=null)
                .sorted(Comparator.comparing(Paiement::getDatePaiement).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

}
