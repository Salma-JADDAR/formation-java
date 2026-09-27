package Entity;

public sealed class Compte permits CompteCourant, CompteEpargne {

    private int id;
    private String numero;
    private double solde;
    private int idClient;

    public Compte(int id, String numero, double solde, int idClient) {
        this.id = id;
        this.numero = numero;
        this.solde = solde;
        this.idClient = idClient;
    }

    public int getId() {
        return id;
    }

    public String getNumero() {
        return numero;
    }

    public double getSolde() {
        return solde;
    }

    public int getIdClient() {
        return idClient;
    }
}