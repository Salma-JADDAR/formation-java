package modules;
import java.util.HashSet;
import java.util.Set;
import Enums.TypeCompte;
public class Compte {
    private String numcomp;
    private Double solde;
    private TypeCompte typeCompte;
    private Set<Transaction> historiqueTransactions ;

    public Compte(String numcomp,Double solde,TypeCompte typeCompte){
        this.numcomp=numcomp;
        this.solde=solde;
        this.typeCompte=typeCompte;
        this.historiqueTransactions =new HashSet<Transaction>();
    }

    public Set<Transaction> getHistoriqueTransactions() {
        return historiqueTransactions;
    }

    public String getNumcomp() {
        return numcomp;
    }

    public Double getSolde() {
        return solde;
    }

    public TypeCompte getTypeCompte() {
        return typeCompte;
    }

    public void setSolde(Double s){
        this.solde=s;
    }

    public void setTypeCompte(TypeCompte type){
        this.typeCompte=type;
    }
}
