package org.telexpertise.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "signes_vitaux")
public class SigneVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double temperature;
    private Integer frequenceCardiaque;
    private Integer tensionSystolique;
    private Integer tensionDiastolique;
    private Double poids;
    private Double taille;

    @Column(nullable = false)
    private LocalDateTime dateMesure;

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;



    public SigneVital(Patient patient, Double temperature, Integer frequenceCardiaque, Integer tensionSystolique,
                      Integer tensionDiastolique,
                      Double poids,
                      Double taille) {

        this.patient = patient;
        this.temperature = temperature;
        this.frequenceCardiaque = frequenceCardiaque;
        this.tensionSystolique = tensionSystolique;
        this.tensionDiastolique = tensionDiastolique;
        this.poids = poids;
        this.taille = taille;
        this.dateMesure = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Integer getFrequenceCardiaque() {
        return frequenceCardiaque;
    }

    public Integer getTensionSystolique() {
        return tensionSystolique;
    }

    public Integer getTensionDiastolique() {
        return tensionDiastolique;
    }

    public Double getPoids() {
        return poids;
    }

    public Double getTaille() {
        return taille;
    }

    public LocalDateTime getDateMesure() {
        return dateMesure;
    }
}