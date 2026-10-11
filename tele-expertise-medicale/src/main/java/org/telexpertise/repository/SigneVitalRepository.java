
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.SigneVital;
import org.telexpertise.util.EntityManagerUtil;

public class SigneVitalRepository {

    public SigneVital save(SigneVital signeVital) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(signeVital);
            transaction.commit();

            return signeVital;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<SigneVital> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(SigneVital.class, id)
            );
        } finally {
            em.close();
        }
    }

    public List<SigneVital> findByPatientId(Long patientId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT s FROM SigneVital s "
                                    + "WHERE s.patient.id = :patientId "
                                    + "ORDER BY s.dateMesure DESC",
                            SigneVital.class
                    )
                    .setParameter("patientId", patientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<SigneVital> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM SigneVital s "
                            + "ORDER BY s.dateMesure DESC",
                    SigneVital.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public SigneVital update(SigneVital signeVital) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            SigneVital updatedSigneVital = em.merge(signeVital);

            transaction.commit();

            return updatedSigneVital;
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

            SigneVital signeVital = em.find(SigneVital.class, id);

            if (signeVital != null) {
                em.remove(signeVital);
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