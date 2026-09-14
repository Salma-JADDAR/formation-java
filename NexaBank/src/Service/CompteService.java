package Service;
import Execptions.FichierException;
import modules.Compte;
import modules.Transaction;
import modules.Client;

import java.io.FileWriter;
import java.io.IOException;

public class CompteService {


    public boolean deja(Compte c,Transaction t){
        for(Transaction tra:c.getHistoriqueTransactions()){
             if(t.getIdTransaction()==tra.getIdTransaction()){
                 return true;
             }
        }
        return false;
    }

    public boolean ajouterTransaction(Transaction t, Compte c){
        if (!deja(c,t)){
            c.getHistoriqueTransactions().add(t);
            System.out.println("tu as un nouvelle transacrtion");
            return true;
        }else{
            System.out.println("tu as deja cette transacrtion");
            return false;
        }
    }

    public boolean supprimerTransaction(Transaction t, Compte c){
        if (deja(c,t)){
            c.getHistoriqueTransactions().remove(t);
            System.out.println("tu as supprimer le  transacrtion"+t.getIdTransaction());
            return true;
        }else{
            System.out.println("cette transacrtion n'existe pas");
            return false;
        }
    }


    public void enregitrerDansUnfichier(Compte cmp)throws FichierException {

            String nomFichier = "compte"+ cmp.getNumcomp()+".txt";
         try(  FileWriter f= new FileWriter(nomFichier)) {;
            f.write("Releve Bnacaire de compte"+cmp.getNumcomp()+"\n");
            for(Transaction t:cmp.getHistoriqueTransactions()){
                f.write("le numero de transaction est :"+t.getIdTransaction()+"\n");
                f.write("Transaction de type :"+t.getType()+"\n");
                f.write("Montant est :"+t.getMontant()+"\n");
                f.write("est fait le :"+t.getDate()+"\n");

            }
            f.write("///////////////////////////////////////////");
            f.close();
        } catch (IOException e) {
            throw new FichierException("Erreur lors de l'enregistrement du relevé du compte " + cmp.getNumcomp());
        }

    }


}
