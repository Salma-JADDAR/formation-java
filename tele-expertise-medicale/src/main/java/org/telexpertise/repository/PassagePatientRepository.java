
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.PassagePatient;
import org.telexpertise.util.EntityManagerUtil;

public class PassagePatientRepository {

    public PassagePatient save(PassagePatient passagePatient) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(passagePatient);
            transaction.commit();

            return passagePatient;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<PassagePatient> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(PassagePatient.class, id)
            );
        } finally {
            em.close();
        }
    }

    public List<PassagePatient> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM PassagePatient p "
                            + "ORDER BY p.dateArrivee ASC",
                    PassagePatient.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<PassagePatient> findByDate(LocalDate date) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            LocalDateTime debutJournee = date.atStartOfDay();
            LocalDateTime debutJourneeSuivante = date.plusDays(1).atStartOfDay();

            return em.createQuery(
                            "SELECT p FROM PassagePatient p "
                                    + "WHERE p.dateArrivee >= :debut "
                                    + "AND p.dateArrivee < :fin "
                                    + "ORDER BY p.dateArrivee ASC",
                            PassagePatient.class
                    )
                    .setParameter("debut", debutJournee)
                    .setParameter("fin", debutJourneeSuivante)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<PassagePatient> findByStatut(String statut) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT p FROM PassagePatient p "
                                    + "WHERE p.statut = :statut "
                                    + "ORDER BY p.dateArrivee ASC",
                            PassagePatient.class
                    )
                    .setParameter("statut", Enum.valueOf(
                            org.telexpertise.enums.StatutPassage.class,
                            statut
                    ))
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public PassagePatient update(PassagePatient passagePatient) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            PassagePatient updatedPassage = em.merge(passagePatient);

            transaction.commit();

            return updatedPassage;
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

            PassagePatient passage = em.find(PassagePatient.class, id);

            if (passage != null) {
                em.remove(passage);
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