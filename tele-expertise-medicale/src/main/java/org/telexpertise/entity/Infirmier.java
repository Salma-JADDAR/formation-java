package org.telexpertise.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.telexpertise.enums.Role;

@Entity
@Table(name = "infirmiers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Infirmier extends User {

    protected Infirmier() {
    }

    public Infirmier(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, Role.INFIRMIER);
    }
}