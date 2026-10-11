
package org.telexpertise.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "dossiers_medicaux")
public class DossierMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "antecedents_medicaux", columnDefinition = "TEXT")
    private String antecedentsMedicaux;

    @Column(name = "allergies", columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "traitements_en_cours", columnDefinition = "TEXT")
    private String traitementsEnCours;

    @Column(name = "date_creation", nullable = false)
    private LocalDate dateCreation;

    @OneToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Patient patient;

    protected DossierMedical() {
    }

    public DossierMedical(
            Patient patient,
            String antecedentsMedicaux,
            String allergies,
            String traitementsEnCours,
            LocalDate dateCreation) {
        this.patient = patient;
        this.antecedentsMedicaux = antecedentsMedicaux;
        this.allergies = allergies;
        this.traitementsEnCours = traitementsEnCours;
        this.dateCreation = dateCreation;
    }

    public Long getId() {
        return id;
    }

    public String getAntecedentsMedicaux() {
        return antecedentsMedicaux;
    }

    public void setAntecedentsMedicaux(String antecedentsMedicaux) {
        this.antecedentsMedicaux = antecedentsMedicaux;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getTraitementsEnCours() {
        return traitementsEnCours;
    }

    public void setTraitementsEnCours(String traitementsEnCours) {
        this.traitementsEnCours = traitementsEnCours;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}