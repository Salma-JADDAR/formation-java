package Service;
import Enums.TypeCompte;
import modules.Client;
import modules.Compte;
import Execptions.CompteInexistantException;
import Execptions.ClientnexistantException;
import java.util.Map;
import java.util.HashMap;
public class BanquierService {

    private Map<Integer, Client> clients = new HashMap<>();

    public boolean creerCompte(Client c,Compte cmp){
        if(!deja(c, cmp.getNumcomp())){
             c.getComptes().put(cmp.getNumcomp(),cmp);
             System.out.println("vous ajouter avec bon le compte");
             return true;
        }else{
            System.out.println("deja ce compte est la ");
            return false;
        }
    }

    public boolean deja(Client c, String numcomp) {
        for (String key : c.getComptes().keySet()) {
            if (key.equals(numcomp)) {
                return true;
            }
        }
        return false;
    }

    public boolean supprimerCompte(Client c,Compte cmp)  throws CompteInexistantException {
        if(deja(c, cmp.getNumcomp())){
            c.getComptes().remove(cmp.getNumcomp(),cmp);
            System.out.println("vous supprimer avec bon le compte");
            return true;
        }else{
            throw new CompteInexistantException("Le compte " + cmp.getNumcomp() + " n'existe pas.");

        }
    }

   public boolean modifierinfoCompte(Client c,Compte cmp,TypeCompte type) throws CompteInexistantException{
       if(deja(c, cmp.getNumcomp())){
           cmp.setTypeCompte(type);
           System.out.println("tu fait la modification avaec succes tres bien")   ;
           return true;
       }else{
           throw new CompteInexistantException("Le compte " + cmp.getNumcomp() + " n'existe pas.");
       }
   }

   public boolean dejaclient(int id){
        for(Integer key : clients.keySet()){
            if(key.equals(id)){
                return true;
            }
       }
        return false;
   }

   public boolean ajouterClient(Client c){
        if(!dejaclient(c.getIdClient())){
            clients.put(c.getIdClient(),c);
            System.out.println("vous ajouter un client bravo");
            return true;
        }else{
            System.out.println("ce client existe deja bien ");
            return true;
        }
   }

//    public boolean supprimerClient(Client c) throws ClientnexistantException{
//        if(dejaclient(c.getIdClient())){
//            clients.remove(c.getIdClient(),c);
//            System.out.println("vous supprimer un client bravo");
//            return true;
//        }else{
//            throw new ClientnexistantException("Le client " + c.getIdClient() + " n'existe pas.");
//        }
//    }
//
//   public boolean modifierinfoclient(Client c,String nom,String prenom,String motDepass,String email)throws ClientnexistantException{
//       if(dejaclient(c.getIdClient())) {
//           c.setEmail(email);
//           c.setMotDepass(motDepass);
//           c.setNom(nom);
//           c.setPrenom(prenom);
//           System.out.println("tous les info de client sont bien modifier ");
//           return true;
//       }else{
//           throw new ClientnexistantException("Le client " + c.getIdClient() + " n'existe pas.");
//       }
//   }
//
//
//
//
//}
