package Service;

import DAO.AbonnementDAO;
import DAO.PaiementDAO;
import Entity.Abonnement;
import Entity.AbonnementAvecEngagement;
import Entity.AbonnementSansEngagement;
import Entity.Paiement;
import Enums.StatutPaiement;
import Enums.TypePaiement;
import Exceptions.AbonnementNotFoundException;
import Exceptions.PaiementAlreadyExistsException;
import Exceptions.PaiementNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaiementService {
    private PaiementDAO pd;
    private AbonnementDAO ad;

    public PaiementService(){
        pd=new PaiementDAO();
        ad=new AbonnementDAO();
    }

    public void enregistrerPaiement(String id, LocalDate dateEcheance, TypePaiement type)throws AbonnementNotFoundException, PaiementAlreadyExistsException {
        Optional<Abonnement>op=ad.findById(id);
        if(op.isPresent()){
          LocalDate datepaiment =LocalDate.now();
            StatutPaiement statut;
            if(datepaiment.isAfter(dateEcheance)){
                statut=StatutPaiement.EN_RETARD;
            }else{
                statut=StatutPaiement.PAYE;
            }
            Paiement p=new Paiement(id,dateEcheance,datepaiment,type,statut);
            pd.create(p);
        }else{
            throw  new AbonnementNotFoundException("l'abonnement n'existe pas");
        }
    }

    public void modification(String id, LocalDate dateEcheance,LocalDate datepaiment,TypePaiement type)throws PaiementNotFoundException{
        Optional<Paiement>op=pd.findById(id);
        if(op.isPresent()){
            Paiement p= op.get();
            p.setDateEcheance(dateEcheance);
            p.setDatePaiement(datepaiment);
            p.setType(type);
            if(datepaiment==null){
                p.setStatut(StatutPaiement.NON_PAYE);
            }else{
                if(datepaiment.isAfter(dateEcheance)){
                    p.setStatut(StatutPaiement.EN_RETARD);
                }else{
                    p.setStatut(StatutPaiement.PAYE);
                }
            }
            pd.update(p);
        }else{
            throw new PaiementNotFoundException("le paiment no trouver");
        }
    }

    public void suppressio(Paiement p) throws PaiementNotFoundException{
        pd.delete(p);
    }

    public List<Paiement>detecterImpayes(String id)throws AbonnementNotFoundException{
        Optional<Abonnement>op=ad.findById(id);
        if(op.isPresent()){
            Abonnement a=op.get();
            if(!(a instanceof AbonnementSansEngagement)){
                return new ArrayList<>();
            }
            return pd.findUnpaidByAbonnement(a);
        }else{
            throw new AbonnementNotFoundException("L'abonnement n'existe pas.") ;
        }
    }

    private Double getMontantPaiment(Paiement p){
            Optional<Abonnement>op=ad.findById(p.getIdAbonnement());
            if(op.isPresent()){
                return op.get().getMontantMensuel();
            }
            return 0.0;
    }
    ///////////////////////////:rapports
 //////////////////trouver ch7al khals fi septembre 2026
    public Double rapportMensuel(int mois,int annee){
        return pd.findAll()
                .stream()
                .filter(p->p.getDatePaiement()!=null)
                .filter(p->p.getDatePaiement().getMonthValue()==mois && p.getDatePaiement().getYear()==annee)
                .mapToDouble(this::getMontantPaiment)
                .sum();
    }

 ///////////////knjma3 le paiments dyal l3am kolo
     public Double rapprtAnuuler(int annee)  {
         return pd.findAll()
                 .stream()
                 .filter(p->p.getDatePaiement()!=null)
                 .filter(p->p.getDatePaiement().getYear()==annee)
                 .mapToDouble(this::getMontantPaiment)
                 .sum();
     }


 /////////////////ch7al ba9i ma tkhalsch
    public Double rapportImpayes(){
        return pd.findAll()
                .stream()
                .filter(p->p.getStatut()==StatutPaiement.NON_PAYE)
                .filter(p->{
                    Optional<Abonnement>op=ad.findById(p.getIdAbonnement());
                    return op.isPresent() && op.get() instanceof AbonnementAvecEngagement;
                })
                .mapToDouble(this::getMontantPaiment)
                .sum();

    }

}
