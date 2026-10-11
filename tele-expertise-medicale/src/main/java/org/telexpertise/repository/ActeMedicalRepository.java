package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.telexpertise.entity.ActeMedical;
import org.telexpertise.util.EntityManagerUtil;

import java.util.List;
import java.util.Optional;

public class ActeMedicalRepository {

    public ActeMedical save(ActeMedical acteMedical) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(acteMedical);
            tx.commit();
            return acteMedical;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<ActeMedical> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(ActeMedical.class, id)
            );
        } finally {
            em.close();
        }
    }

    public List<ActeMedical> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT a FROM ActeMedical a ORDER BY a.dateRealisation DESC",
                    ActeMedical.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<ActeMedical> findByConsultationId(Long consultationId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT a FROM ActeMedical a " +
                                    "WHERE a.consultation.id = :consultationId " +
                                    "ORDER BY a.dateRealisation DESC",
                            ActeMedical.class
                    )
                    .setParameter("consultationId", consultationId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public ActeMedical update(ActeMedical acteMedical) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            ActeMedical updated = em.merge(acteMedical);
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

            ActeMedical acteMedical =
                    em.find(ActeMedical.class, id);

            if (acteMedical == null) {
                tx.commit();
                return false;
            }

            em.remove(acteMedical);
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

