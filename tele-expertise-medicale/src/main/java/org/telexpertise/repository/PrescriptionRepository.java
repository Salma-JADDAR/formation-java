package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.telexpertise.entity.Prescription;
import org.telexpertise.util.EntityManagerUtil;

import java.util.List;
import java.util.Optional;

public class PrescriptionRepository {

    public Prescription save(Prescription prescription) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(prescription);
            tx.commit();
            return prescription;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Prescription> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(Prescription.class, id)
            );
        } finally {
            em.close();
        }
    }

    public List<Prescription> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Prescription p ORDER BY p.datePrescription DESC",
                    Prescription.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public Optional<Prescription> findByConsultationId(Long consultationId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            List<Prescription> prescriptions = em.createQuery(
                            "SELECT p FROM Prescription p " +
                                    "WHERE p.consultation.id = :consultationId",
                            Prescription.class
                    )
                    .setParameter("consultationId", consultationId)
                    .setMaxResults(1)
                    .getResultList();

            return prescriptions.stream().findFirst();
        } finally {
            em.close();
        }
    }

    public Prescription update(Prescription prescription) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Prescription updated = em.merge(prescription);
            tx.commit();
            return updated;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public boolean deleteById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            Prescription prescription =
                    em.find(Prescription.class, id);

            if (prescription == null) {
                tx.commit();
                return false;
            }

            em.remove(prescription);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}

