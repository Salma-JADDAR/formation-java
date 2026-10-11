
package org.telexpertise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "signes_vitaux")
public class SigneVital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature", precision = 4, scale = 1)
    private BigDecimal temperature;

    @Column(name = "frequence_cardiaque")
    private Integer frequenceCardiaque;

    @Column(name = "tension_systolique")
    private Integer tensionSystolique;

    @Column(name = "tension_diastolique")
    private Integer tensionDiastolique;

    @Column(name = "poids", precision = 5, scale = 2)
    private BigDecimal poids;

    @Column(name = "taille", precision = 4, scale = 2)
    private BigDecimal taille;

    @Column(name = "date_mesure", nullable = false)
    private LocalDateTime dateMesure;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    protected SigneVital() {
    }

    public SigneVital(Patient patient, BigDecimal temperature, Integer frequenceCardiaque, Integer tensionSystolique, Integer tensionDiastolique, BigDecimal poids, BigDecimal taille, LocalDateTime dateMesure) {
        this.patient = patient;
        this.temperature = temperature;
        this.frequenceCardiaque = frequenceCardiaque;
        this.tensionSystolique = tensionSystolique;
        this.tensionDiastolique = tensionDiastolique;
        this.poids = poids;
        this.taille = taille;
        this.dateMesure = dateMesure;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public Integer getFrequenceCardiaque() {
        return frequenceCardiaque;
    }

    public void setFrequenceCardiaque(Integer frequenceCardiaque) {
        this.frequenceCardiaque = frequenceCardiaque;
    }

    public Integer getTensionSystolique() {
        return tensionSystolique;
    }

    public void setTensionSystolique(Integer tensionSystolique) {
        this.tensionSystolique = tensionSystolique;
    }

    public Integer getTensionDiastolique() {
        return tensionDiastolique;
    }

    public void setTensionDiastolique(Integer tensionDiastolique) {
        this.tensionDiastolique = tensionDiastolique;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public BigDecimal getTaille() {
        return taille;
    }

    public void setTaille(BigDecimal taille) {
        this.taille = taille;
    }

    public LocalDateTime getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(LocalDateTime dateMesure) {
        this.dateMesure = dateMesure;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}