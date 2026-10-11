
package org.telexpertise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.telexpertise.enums.Role;

import java.math.BigDecimal;

@Entity
@Table(name = "specialistes")
@PrimaryKeyJoinColumn(name = "user_id")
public class Specialiste extends User {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialite_id")
    private Specialite specialite;

    private BigDecimal tarif;

    protected Specialiste() {
    }

    public Specialiste(String nom, String prenom, String email, String motDePasse, Specialite specialite, BigDecimal tarif) {

        super(nom, prenom, email, motDePasse, Role.SPECIALISTE);
        this.specialite = specialite;
        this.tarif = tarif;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
    }

    public BigDecimal getTarif() {
        return tarif;
    }

    public void setTarif(BigDecimal tarif) {
        this.tarif = tarif;
    }
}