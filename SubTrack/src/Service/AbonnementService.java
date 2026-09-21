package Service;
import DAO.AbonnementDAO;
import Entity.Abonnement;
import Enums.StatutAbonnement;
import Exceptions.AbonnementAlreadyExistsException;
import Exceptions.AbonnementNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbonnementService {
    private AbonnementDAO ad;

    public AbonnementService(){
        ad=new AbonnementDAO();
    }

    public void creation(Abonnement a)throws AbonnementAlreadyExistsException {
        ad.create(a);
    }

    public void modification(String id,String nomService, Double montantMensuel, LocalDate dateDebut, LocalDate dateFin, StatutAbonnement statut) throws AbonnementNotFoundException {
        Optional<Abonnement>op=ad.findById(id);
        if(op.isPresent()){
            Abonnement a = op.get();
            a.setNomService(nomService);
            a.setMontantMensuel(montantMensuel);
            a.setDateDebut(dateDebut);
            a.setDateFin(dateFin);
            a.setStatut(statut);
            ad.update(a);
        }else{
            throw new AbonnementNotFoundException ("abonnemnt n'existe pas");
        }
    }

    public void  suppression(Abonnement a) throws AbonnementNotFoundException {
        ad.delete(a);
    }

    public void resilier(String id)throws AbonnementNotFoundException {
        Optional<Abonnement>op=ad.findById(id);
        if(op.isPresent()){
            Abonnement a=op.get();
            a.setStatut(StatutAbonnement.RESILIE);
            ad.update(a);
        }else{
            throw  new AbonnementNotFoundException ("abonnemnt n'existe pas");
        }
    }

    public List<LocalDate>générationEchance(String id) throws AbonnementNotFoundException {
        Optional<Abonnement>op=ad.findById(id);
        if(op.isPresent()){
            Abonnement a=op.get();
            List<LocalDate>echances=new ArrayList<>();
            LocalDate e= a.getDateDebut().plusMonths(1);
            while(!e.isAfter(a.getDateFin())){
                echances.add(e);
                e=e.plusMonths(1);
            }
        }else{
            throw  new AbonnementNotFoundException ("abonnemnt n'existe pas");
        }
    }

}
