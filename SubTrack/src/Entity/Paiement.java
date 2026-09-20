package Entity;

import Enums.StatutPaiement;
import Enums.TypePaiement;

import java.time.LocalDate;
import java.util.UUID;

public class Paiement {
    private String idPaiement;
    private String idAbonnement;
    private LocalDate dateEcheance;
    private LocalDate datePaiement;
    private TypePaiement type;
    private StatutPaiement statut;

    public Paiement(String idAbonnement,LocalDate dateEcheance,LocalDate datePaiement,TypePaiement type,StatutPaiement statut){
        this.idPaiement= UUID.randomUUID().toString();
        this.idAbonnement=idAbonnement;
        this.dateEcheance=dateEcheance;
        this.datePaiement=datePaiement;
        this.type=type;
        this.statut=statut;
    }

    public String getIdPaiement() {
        return idPaiement;
    }

    public String getIdAbonnement() {
        return idAbonnement;
    }

    public void setIdAbonnement(String idAbonnement) {
        this.idAbonnement = idAbonnement;
    }

    public LocalDate getDateEcheance() {
        return dateEcheance;
    }

    public void setDateEcheance(LocalDate dateEcheance) {
        this.dateEcheance = dateEcheance;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public TypePaiement getType() {
        return type;
    }

    public void setType(TypePaiement type) {
        this.type = type;
    }

    public StatutPaiement getStatut() {
        return statut;
    }

    public void setStatut(StatutPaiement statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Paiement{" + "idPaiement='" + idPaiement + ", idAbonnement='" + idAbonnement + ", dateEcheance=" + dateEcheance + ", datePaiement=" + datePaiement + ", type=" + type + ", statut=" + statut + '}';
    }
}
