
package org.telexpertise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import org.telexpertise.enums.StatutPassage;

@Entity
@Table(name = "passages_patients")
public class PassagePatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_arrivee", nullable = false)
    private LocalDateTime dateArrivee;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 20)
    private StatutPassage statut;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    protected PassagePatient() {
    }

    public PassagePatient(Patient patient) {
        this.patient = patient;
        this.dateArrivee = LocalDateTime.now();
        this.statut = StatutPassage.EN_ATTENTE;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateArrivee() {
        return dateArrivee;
    }

    public StatutPassage getStatut() {
        return statut;
    }

    public void setStatut(StatutPassage statut) {
        this.statut = statut;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}