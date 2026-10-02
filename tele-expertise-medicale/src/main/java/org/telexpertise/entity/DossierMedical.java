package org.telexpertise.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dossiers_medicaux")
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Patient patient;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    public DossierMedical() {
    }

    public DossierMedical(Patient patient) {
        this.patient = patient;
        this.dateCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}