//package modules;
//import Enums.TypeTransaction;
//import java.time.LocalDateTime;
//
//public class Transaction {
//    private int idTransaction;
//    private Double montant;
//    private LocalDateTime date;
//    private TypeTransaction type;
//    private Compte compteSource;
//    private Compte compteDestination;
//
//    public Transaction(int idTransaction,Double montant,LocalDateTime date, TypeTransaction type,Compte compteSource,Compte compteDestination){
//        this.idTransaction=idTransaction;
//        this.montant=montant;
//        this.date=date;
//        this.type=type;
//        this.compteSource=compteSource;
//        this.compteDestination=compteDestination;
//    }
//
//    public int getIdTransaction(){
//        return idTransaction;
//    }
//
//    public Double getMontant() {
//        return montant;
//    }
//
//    public Compte getCompteDistination() {
//        return compteDestination;
//    }
//
//    public Compte getCompteSource() {
//        return compteSource;
//    }
//
//    public TypeTransaction getType() {
//        return type;
//    }
//
//    public void setIdTransaction(int idTransaction){
//        this.idTransaction=idTransaction;
//    }
//
//    public void setMontant(Double montant){
//        this.montant=montant;
//    }
//
//    public void setCompteSource(Compte c){
//        this.compteSource=c;
//    }
//
//    public void setCompteDistination(Compte c){
//        this.compteDestination=c;
//    }
//
//    public LocalDateTime getDate() {
//        return date;
//    }
//
//
//    public void setType(TypeTransaction t){
//        this.type=type;
//    }
//
//    public void setDate(LocalDateTime date) {
//        this.date = date;
//    }
//
//    public void afficher_Transaction(){
//        System.out.println("le transaction a un montant de "+getMontant()+"qui transformer de "+getCompteSource()+"a"+getCompteDistination()+"a la date"+getDate());
//    }
//}
