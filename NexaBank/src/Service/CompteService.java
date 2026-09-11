//package Service;
//import modules.Compte;
//import modules.Transaction;
//import modules.Client;
//public class CompteService {
//
//
//    public boolean deja(Compte c,Transaction t){
//        for(Transaction tra:c.getHistoriqueTransactions()){
//             if(t.getIdTransaction()==tra.getIdTransaction()){
//                 return true;
//             }
//        }
//        return false;
//    }
//
//    public boolean ajouterTransaction(Transaction t, Compte c){
//        if (!deja(c,t)){
//            c.getHistoriqueTransactions().add(t);
//            System.out.println("tu as un nouvelle transacrtion");
//            return true;
//        }else{
//            System.out.println("tu as deja cette transacrtion");
//            return false;
//        }
//    }
//
//    public boolean supprimerTransaction(Transaction t, Compte c){
//        if (deja(c,t)){
//            c.getHistoriqueTransactions().remove(t);
//            System.out.println("tu as supprimer le  transacrtion"+t.getIdTransaction());
//            return true;
//        }else{
//            System.out.println("cette transacrtion n'existe pas");
//            return false;
//        }
//    }
//
//
//
//}
