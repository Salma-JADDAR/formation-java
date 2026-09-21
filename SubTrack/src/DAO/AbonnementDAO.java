package DAO;

import Entity.Abonnement;
import Entity.AbonnementAvecEngagement;
import Entity.AbonnementSansEngagement;
import Enums.StatutAbonnement;
import Exceptions.AbonnementAlreadyExistsException;

import java.util.*;
import java.util.stream.Collectors;

public class AbonnementDAO {
    Map<String, Abonnement> abonnements;

    public AbonnementDAO(){
        abonnements=new HashMap<>();
    }

    public void create(Abonnement abonnement)throws AbonnementAlreadyExistsException {
        if(!abonnements.containsKey(abonnement.getId())){
            abonnements.put(abonnement.getId(), abonnement);
        }else{
            throw new AbonnementAlreadyExistsException("l'abonnemnt est deja existe dans notre collection");

        }
    }

    public Optional<Abonnement>findById (String id){
        return Optional.ofNullable(abonnements.get(id));
    }

    public List<Abonnement>findAll(){
       return  new ArrayList<>(abonnements.values());
    }

    public void update(Abonnement a) throws AbonnementAlreadyExistsException{
          if(abonnements.containsKey(a.getId())){
              abonnements.put(a.getId(),a);
          }else{
              throw new AbonnementAlreadyExistsException(" l'abonnement n'existe pas");
          }
    }

    public void delete(Abonnement a)throws AbonnementAlreadyExistsException{
        if(abonnements.containsKey(a.getId())){
            abonnements.remove(a.getId());
        }else{
            throw new AbonnementAlreadyExistsException(" l'abonnement n'existe pas");
        }
    }

    public List<Abonnement> findActiveSubscriptions(){
        return abonnements.values()
                .stream()
                .filter(a->a.getStatut()== StatutAbonnement.Actif)
                .collect(Collectors.toList());
    }

    public List<Abonnement> findAvecEngagement() {
        return abonnements.values()
                .stream()
                .filter(a -> a instanceof AbonnementAvecEngagement)
                .collect(Collectors.toList());
    }

    public List<Abonnement> findSansEngagement(){
        return abonnements.values()
                .stream()
                .filter(a->a instanceof AbonnementSansEngagement)
                .collect(Collectors.toList());
    }


}
