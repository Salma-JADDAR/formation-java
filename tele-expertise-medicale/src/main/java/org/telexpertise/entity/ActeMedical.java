package org.telexpertise.entity;

import jakarta.persistence.*;
import org.telexpertise.enums.TypeActeMedical;

import java.time.LocalDateTime;

@Entity
@Table(name = "actes_medicaux")
public class ActeMedical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeActeMedical type;

    @Column(nullable = false)
    private Double cout;

    @Column(nullable = false)
    private LocalDateTime dateActe;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consultation_id", nullable = false)
    private Consultation consultation;

    public ActeMedical() {
    }

    public ActeMedical(TypeActeMedical type,
                       Double cout,
                       Consultation consultation) {

        this.type = type;
        this.cout = cout;
        this.consultation = consultation;
        this.dateActe = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public TypeActeMedical getType() {
        return type;
    }

    public Double getCout() {
        return cout;
    }

    public LocalDateTime getDateActe() {
        return dateActe;
    }

    public Consultation getConsultation() {
        return consultation;
    }
}