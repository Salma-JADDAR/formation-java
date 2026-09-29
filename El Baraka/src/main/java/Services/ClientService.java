package Services;

import DAO.ClientDAO;
import DAO.CompteDAO;
import Entity.Client;
import Entity.Compte;
import Exceptions.ClientNotFoundException;

import java.util.List;

public class ClientService {

    private ClientDAO clientDAO = new ClientDAO();
    private CompteDAO compteDAO =new CompteDAO();
    public void ajouter(Client client) {
        clientDAO.ajouter(client);
    }

    public void modifier(int id, String nom, String email, String payshabitue) throws ClientNotFoundException {
        Client client = clientDAO.rechercherByid(id);
        if (client == null) {
            throw new ClientNotFoundException("Client introuvable");
        }
        clientDAO.modifier(id, nom, email, payshabitue);
    }

    public void supprimer(int id) throws ClientNotFoundException {
        Client client = clientDAO.rechercherByid(id);
        if (client == null) {
            throw new ClientNotFoundException("Client introuvable");
        }
        clientDAO.supprimer(id);
    }

    public Client rechercherParId(int id) throws ClientNotFoundException {
        Client client = clientDAO.rechercherByid(id);
        if (client == null) {
            throw new ClientNotFoundException("Client introuvable");
        }
        return client;
    }

    public List<Client> rechercherParNom(String nom) {
        return clientDAO.rechercherByNom(nom);
    }

    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    public long nombretotaldescomptes(int idclient){
        return compteDAO.findAll()
                .stream()
                .filter(c->c.getIdClient()==idclient)
                .count();

    }

    public double soldetotale(int idclient){
        return compteDAO.findAll()
                .stream()
                .filter(c->c.getIdClient()==idclient)
                .mapToDouble(Compte::getSolde)
                .sum();
    }
}