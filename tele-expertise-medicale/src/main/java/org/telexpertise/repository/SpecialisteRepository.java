
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.Specialiste;
import org.telexpertise.util.EntityManagerUtil;

public class SpecialisteRepository {

    public Specialiste save(Specialiste specialiste) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(specialiste);
            transaction.commit();
            return specialiste;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<Specialiste> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(em.find(Specialiste.class, id));
        } finally {
            em.close();
        }
    }

    public Optional<Specialiste> findByEmail(String email) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            List<Specialiste> result = em.createQuery(
                            "SELECT s FROM Specialiste s WHERE s.email = :email",
                            Specialiste.class
                    )
                    .setParameter("email", email)
                    .setMaxResults(1)
                    .getResultList();

            return result.stream().findFirst();
        } finally {
            em.close();
        }
    }

    public List<Specialiste> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM Specialiste s "
                            + "ORDER BY s.nom ASC, s.prenom ASC",
                    Specialiste.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Specialiste> findBySpecialiteId(Long specialiteId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT s FROM Specialiste s "
                                    + "WHERE s.specialite.id = :specialiteId "
                                    + "ORDER BY s.nom ASC, s.prenom ASC",
                            Specialiste.class
                    )
                    .setParameter("specialiteId", specialiteId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Specialiste> findBySpecialiteIdAndTarifLessThanEqual(
            Long specialiteId, java.math.BigDecimal tarifMax) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT s FROM Specialiste s "
                                    + "WHERE s.specialite.id = :specialiteId "
                                    + "AND s.tarif <= :tarifMax "
                                    + "ORDER BY s.tarif ASC",
                            Specialiste.class
                    )
                    .setParameter("specialiteId", specialiteId)
                    .setParameter("tarifMax", tarifMax)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Specialiste update(Specialiste specialiste) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Specialiste updated = em.merge(specialiste);
            transaction.commit();
            return updated;
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
            Specialiste specialiste = em.find(Specialiste.class, id);

            if (specialiste != null) {
                em.remove(specialiste);
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