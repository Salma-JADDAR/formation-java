
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.Consultation;
import org.telexpertise.util.EntityManagerUtil;

public class ConsultationRepository {

    public Consultation save(Consultation consultation) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(consultation);
            transaction.commit();

            return consultation;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<Consultation> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(Consultation.class, id)
            );
        } finally {
            em.close();
        }
    }

    public List<Consultation> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Consultation c "
                            + "ORDER BY c.dateConsultation DESC",
                    Consultation.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Consultation> findByPatientId(Long patientId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT c FROM Consultation c "
                                    + "WHERE c.patient.id = :patientId "
                                    + "ORDER BY c.dateConsultation DESC",
                            Consultation.class
                    )
                    .setParameter("patientId", patientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Consultation> findByGeneralisteId(Long generalisteId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT c FROM Consultation c "
                                    + "WHERE c.generaliste.id = :generalisteId "
                                    + "ORDER BY c.dateConsultation DESC",
                            Consultation.class
                    )
                    .setParameter("generalisteId", generalisteId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Consultation> findByDate(LocalDate date) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            LocalDateTime debutJournee = date.atStartOfDay();
            LocalDateTime debutJourneeSuivante =
                    date.plusDays(1).atStartOfDay();

            return em.createQuery(
                            "SELECT c FROM Consultation c "
                                    + "WHERE c.dateConsultation >= :debut "
                                    + "AND c.dateConsultation < :fin "
                                    + "ORDER BY c.dateConsultation ASC",
                            Consultation.class
                    )
                    .setParameter("debut", debutJournee)
                    .setParameter("fin", debutJourneeSuivante)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Consultation update(Consultation consultation) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Consultation updatedConsultation = em.merge(consultation);

            transaction.commit();

            return updatedConsultation;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public void deleteById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Consultation consultation =
                    em.find(Consultation.class, id);

            if (consultation != null) {
                em.remove(consultation);
            }

            transaction.commit();
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }
}