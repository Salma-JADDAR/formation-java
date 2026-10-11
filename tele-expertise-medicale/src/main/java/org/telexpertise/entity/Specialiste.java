
package org.telexpertise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.telexpertise.enums.Role;

@Entity
@Table(name = "specialistes")
@PrimaryKeyJoinColumn(name = "user_id")
public class Specialiste extends User {

    protected Specialiste() {
    }

    public Specialiste(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, Role.SPECIALISTE);
    }
}