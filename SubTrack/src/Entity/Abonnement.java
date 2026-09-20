package Entity;

import Enums.StatutAbonnement;
import java.time.LocalDate;
import java.util.UUID;

public abstract class Abonnement {
    private String id;
    private String nomService;
    private Double montantMensuel;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatutAbonnement statut;

    public Abonnement(String nomService,Double montantMensuel,LocalDate dateDebut,LocalDate dateFin,StatutAbonnement statut){
        this.id= UUID.randomUUID().toString();
        this.nomService=nomService;
        this.montantMensuel=montantMensuel;
        this.dateDebut=dateDebut;
        this.dateFin=dateFin;
        this.statut=statut;
    }

    public String getId() {
        return id;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }

    public Double getMontantMensuel() {
        return montantMensuel;
    }

    public void setMontantMensuel(Double montantMensuel) {
        this.montantMensuel = montantMensuel;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public StatutAbonnement getStatut() {
        return statut;
    }

    public void setStatut(StatutAbonnement statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id  + ", nomService=" + nomService + ", montantMensuel=" + montantMensuel + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", statut=" + statut + '}';
    }
}
