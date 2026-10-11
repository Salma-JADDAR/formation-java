
package org.telexpertise.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import org.telexpertise.enums.Role;

@Entity
@Table(name = "generalistes")
@PrimaryKeyJoinColumn(name = "user_id")
public class Generaliste extends User {

    protected Generaliste() {
    }

    public Generaliste(String nom, String prenom, String email, String motDePasse) {
        super(nom, prenom, email, motDePasse, Role.GENERALISTE);
    }
}