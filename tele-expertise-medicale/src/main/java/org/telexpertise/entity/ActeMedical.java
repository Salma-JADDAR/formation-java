
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
import org.telexpertise.enums.TypeActeMedical;

@Entity
@Table(name = "actes_medicaux")
public class ActeMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_acte", nullable = false, length = 50)
    private TypeActeMedical typeActe;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "date_realisation")
    private LocalDateTime dateRealisation;

    @Column(name = "resultat", columnDefinition = "TEXT")
    private String resultat;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    protected ActeMedical() {
    }

    public ActeMedical(TypeActeMedical typeActe, String description, Consultation consultation) {
        this.typeActe = typeActe;
        this.description = description;
        this.consultation = consultation;
    }

    public Long getId() {
        return id;
    }

    public TypeActeMedical getTypeActe() {
        return typeActe;
    }

    public void setTypeActe(TypeActeMedical typeActe) {
        this.typeActe = typeActe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateRealisation() {
        return dateRealisation;
    }

    public void setDateRealisation(LocalDateTime dateRealisation) {
        this.dateRealisation = dateRealisation;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
}