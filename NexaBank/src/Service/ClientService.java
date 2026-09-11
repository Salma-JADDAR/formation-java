//package Service;
//
//import Execptions.CompteInexistantException;
//import Execptions.MontantInvalideException;
//import Execptions.SoldeInsuffisantException;
//import modules.Client;
//import modules.Compte;
//
//public class ClientService {
//
//    public void Consulter_solde_de_chaque_Compte(Client c){
//        for(Compte cmp : c.getComptes().values()){
//            System.out.println("numero de compte: " + cmp.getNumcomp() + " solde: " + cmp.getSolde());
//        }
//    }
//
//
//    public boolean dejaCompte(Client c,String numcom){
//        for(String key :c.getComptes().keySet()){
//            if(key.equals(numcom)){
//                return true;
//            }
//        }
//        return false;
//    }
//
//    public boolean depotMontant(Client c, Compte cmp, double montant) throws CompteInexistantException,MontantInvalideException {
//        if(dejaCompte(c, cmp.getNumcomp())){
//            if(montant <=0){
//                throw new MontantInvalideException("le montant qui tu donner est negative");
//            }
//            double  solde =cmp.getSolde();
//            solde=solde+montant;
//            cmp.setSolde(solde);
//            System.out.println("vous ajouter un montant de :"+montant+"a le compte "+cmp.getNumcomp()+"est maintant votre solde est :"+solde);
//            return true;
//        }else{
//            throw new CompteInexistantException("Le compte " + cmp.getNumcomp() + " n'existe pas.");
//        }
//
//    }
//
//    public boolean retraitMontant(Client c,Compte cmp,double montant) throws CompteInexistantException,MontantInvalideException,SoldeInsuffisantException{
//        if(dejaCompte(c, cmp.getNumcomp())){
//            if(montant <=0){
//                throw new MontantInvalideException("le montant doit etre superirue a 0");
//            }
//            if (montant > cmp.getSolde()) {
//                throw new SoldeInsuffisantException("Le montant doit etre inferieure ou egale le solde ");
//            }
//            double  solde =cmp.getSolde();
//            solde=solde-montant;
//            cmp.setSolde(solde);
//            System.out.println("vous reterait un montant de :"+montant+"a le compte "+cmp.getNumcomp()+"est maintant votre solde est :"+solde);
//            return true;
//        }else{
//            throw new CompteInexistantException("Le compte " + cmp.getNumcomp() + " n'existe pas.");
//        }
//
//    }
//
//
//    public boolean vairementMontant(Client c1,Client c2,Compte cmp1, Compte cmp2,double montant) throws CompteInexistantException,MontantInvalideException,SoldeInsuffisantException {
//        if(retraitMontant(c1,cmp1,montant)){
//            depotMontant(c2,cmp2,montant);
//            System.out.println("vous vairement a le compte "+cmp2.getNumcomp()+"est effetcuer bien bravo");
//            return true;
//        }
//        return false;
//
//    }
//}
