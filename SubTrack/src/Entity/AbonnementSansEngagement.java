package Entity;

import Enums.StatutAbonnement;

import java.time.LocalDate;

public class AbonnementSansEngagement extends Abonnement{

    public AbonnementSansEngagement(String nomService, Double montantMensuel, LocalDate dateDebut, LocalDate dateFin, StatutAbonnement statut){
        super(nomService,montantMensuel,dateDebut,dateFin,statut);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
