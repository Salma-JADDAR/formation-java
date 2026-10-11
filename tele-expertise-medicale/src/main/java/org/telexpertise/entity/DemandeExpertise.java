
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
import org.telexpertise.enums.ModeCommunication;
import org.telexpertise.enums.Priorite;
import org.telexpertise.enums.StatutExpertise;

@Entity
@Table(name = "demandes_expertise")
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question", nullable = false, columnDefinition = "TEXT")
    private String question;

    @Enumerated(EnumType.STRING)
    @Column(name = "priorite", nullable = false, length = 20)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut", nullable = false, length = 20)
    private StatutExpertise statut;

    @Enumerated(EnumType.STRING)
    @Column(name = "mode_communication", nullable = false, length = 20)
    private ModeCommunication modeCommunication;

    @Column(name = "avis_medical", columnDefinition = "TEXT")
    private String avisMedical;

    @Column(name = "recommandations", columnDefinition = "TEXT")
    private String recommandations;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime dateCreation;

    @Column(name = "date_reponse")
    private LocalDateTime dateReponse;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    protected DemandeExpertise() {
    }

    public DemandeExpertise(String question, Priorite priorite, ModeCommunication modeCommunication, Consultation consultation, Specialiste specialiste) {
        this.question = question;
        this.priorite = priorite;
        this.modeCommunication = modeCommunication;
        this.consultation = consultation;
        this.specialiste = specialiste;
        this.statut = StatutExpertise.EN_ATTENTE;
        this.dateCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    public StatutExpertise getStatut() {
        return statut;
    }

    public void setStatut(StatutExpertise statut) {
        this.statut = statut;
    }

    public ModeCommunication getModeCommunication() {
        return modeCommunication;
    }

    public void setModeCommunication(ModeCommunication modeCommunication) {
        this.modeCommunication = modeCommunication;
    }

    public String getAvisMedical() {
        return avisMedical;
    }

    public void setAvisMedical(String avisMedical) {
        this.avisMedical = avisMedical;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public LocalDateTime getDateReponse() {
        return dateReponse;
    }

    public void setDateReponse(LocalDateTime dateReponse) {
        this.dateReponse = dateReponse;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }

    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public void setSpecialiste(Specialiste specialiste) {
        this.specialiste = specialiste;
    }
}