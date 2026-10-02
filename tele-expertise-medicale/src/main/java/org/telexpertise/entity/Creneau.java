package org.telexpertise.entity;

import jakarta.persistence.*;
import org.telexpertise.enums.StatutCreneau;

import java.time.LocalDateTime;

@Entity
@Table(name = "creneaux")
public class Creneau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime debut;

    @Column(nullable = false)
    private LocalDateTime fin;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutCreneau statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    public Creneau() {
    }

    public Creneau(LocalDateTime debut,
                   LocalDateTime fin,
                   Specialiste specialiste) {

        this.debut = debut;
        this.fin = fin;
        this.specialiste = specialiste;
        this.statut = StatutCreneau.DISPONIBLE;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDebut() {
        return debut;
    }

    public LocalDateTime getFin() {
        return fin;
    }

    public StatutCreneau getStatut() {
        return statut;
    }

    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public void setStatut(StatutCreneau statut) {
        this.statut = statut;
    }
}