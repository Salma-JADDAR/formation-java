package org.telexpertise.entity;

import jakarta.persistence.*;
import org.telexpertise.enums.Priorite;
import org.telexpertise.enums.StatutExpertise;

import java.time.LocalDateTime;

@Entity
@Table(name = "demandes_expertise")
public class DemandeExpertise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 2000)
    private String question;

    @Column(length = 3000)
    private String donneesMedicales;

    @Column(length = 2000)
    private String analyses;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priorite priorite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutExpertise statut;

    @Column(length = 3000)
    private String avisMedical;

    @Column(length = 3000)
    private String recommandations;

    @Column(nullable = false)
    private LocalDateTime dateCreation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialiste_id", nullable = false)
    private Specialiste specialiste;

    @OneToOne(optional = false)
    @JoinColumn(name = "creneau_id", nullable = false, unique = true)
    private Creneau creneau;

    public DemandeExpertise() {
    }

    public DemandeExpertise(Consultation consultation,
                            Specialiste specialiste,
                            Creneau creneau,
                            String question,
                            String donneesMedicales,
                            String analyses,
                            Priorite priorite) {

        this.consultation = consultation;
        this.specialiste = specialiste;
        this.creneau = creneau;
        this.question = question;
        this.donneesMedicales = donneesMedicales;
        this.analyses = analyses;
        this.priorite = priorite;
        this.statut = StatutExpertise.EN_ATTENTE;
        this.dateCreation = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getDonneesMedicales() {
        return donneesMedicales;
    }

    public String getAnalyses() {
        return analyses;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public StatutExpertise getStatut() {
        return statut;
    }

    public String getAvisMedical() {
        return avisMedical;
    }

    public String getRecommandations() {
        return recommandations;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public Creneau getCreneau() {
        return creneau;
    }

    public void setAvisMedical(String avisMedical) {
        this.avisMedical = avisMedical;
    }

    public void setRecommandations(String recommandations) {
        this.recommandations = recommandations;
    }

    public void setStatut(StatutExpertise statut) {
        this.statut = statut;
    }
}