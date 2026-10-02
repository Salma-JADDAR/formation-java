package org.telexpertise.entity;

import jakarta.persistence.*;
import org.telexpertise.enums.StatutConsultation;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dateConsultation;

    @Column(nullable = false)
    private String motif;

    @Column(length = 2000)
    private String observations;

    @Column(nullable = false)
    private Double cout;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutConsultation statut;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "generaliste_id", nullable = false)
    private User generaliste;

    public Consultation() {
    }

    public Consultation(Patient patient,
                        User generaliste,
                        String motif,
                        String observations) {

        this.patient = patient;
        this.generaliste = generaliste;
        this.motif = motif;
        this.observations = observations;
        this.dateConsultation = LocalDateTime.now();
        this.cout = 150.0;
        this.statut = StatutConsultation.EN_COURS;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateConsultation() {
        return dateConsultation;
    }

    public String getMotif() {
        return motif;
    }

    public String getObservations() {
        return observations;
    }

    public Double getCout() {
        return cout;
    }

    public StatutConsultation getStatut() {
        return statut;
    }

    public Patient getPatient() {
        return patient;
    }

    public User getGeneraliste() {
        return generaliste;
    }

    public void setStatut(StatutConsultation statut) {
        this.statut = statut;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}