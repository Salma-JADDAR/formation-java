package org.telexpertise.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 3000)
    private String contenu;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    public Prescription() {
    }

    public Prescription(String contenu, Consultation consultation) {
        this.contenu = contenu;
        this.consultation = consultation;
    }

    public Long getId() {
        return id;
    }

    public String getContenu() {
        return contenu;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}