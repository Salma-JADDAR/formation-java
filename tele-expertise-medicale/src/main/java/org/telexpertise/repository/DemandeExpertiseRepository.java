package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.telexpertise.entity.DemandeExpertise;
import org.telexpertise.enums.StatutExpertise;
import org.telexpertise.util.EntityManagerUtil;

import java.util.List;
import java.util.Optional;

public class DemandeExpertiseRepository {

    public DemandeExpertise save(DemandeExpertise demande) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(demande);
            tx.commit();
            return demande;
        } catch (RuntimeException e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<DemandeExpertise> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(DemandeExpertise.class, id)
            );
        } finally {
            em.close();
        }
    }

    public List<DemandeExpertise> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT d FROM DemandeExpertise d ORDER BY d.dateCreation DESC",
                    DemandeExpertise.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<DemandeExpertise> findByConsultationId(Long consultationId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT d FROM DemandeExpertise d " +
                                    "WHERE d.consultation.id = :consultationId " +
                                    "ORDER BY d.dateCreation DESC",
                            DemandeExpertise.class
                    )
                    .setParameter("consultationId", consultationId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<DemandeExpertise> findBySpecialisteId(Long specialisteId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT d FROM DemandeExpertise d " +
                                    "WHERE d.specialiste.id = :specialisteId " +
                                    "ORDER BY d.dateCreation DESC",
                            DemandeExpertise.class
                    )
                    .setParameter("specialisteId", specialisteId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<DemandeExpertise> findByStatut(StatutExpertise statut) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT d FROM DemandeExpertise d " +
                                    "WHERE d.statut = :statut " +
                                    "ORDER BY d.dateCreation DESC",
                            DemandeExpertise.class
                    )
                    .setParameter("statut", statut)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public DemandeExpertise update(DemandeExpertise demande) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            DemandeExpertise updated = em.merge(demande);
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

            DemandeExpertise demande =
                    em.find(DemandeExpertise.class, id);

            if (demande == null) {
                tx.commit();
                return false;
            }

            em.remove(demande);
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

