package org.telexpertise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "specialistes")
public class Specialiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialite_id", nullable = false)
    private Specialite specialite;

    @Column(nullable = false)
    private Double tarif;

    public Specialiste() {
    }

    public Specialiste(User user, Specialite specialite, Double tarif) {
        this.user = user;
        this.specialite = specialite;
        this.tarif = tarif;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public Double getTarif() {
        return tarif;
    }

    public void setTarif(Double tarif) {
        this.tarif = tarif;
    }
}