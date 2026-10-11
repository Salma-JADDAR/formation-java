
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.Specialite;
import org.telexpertise.util.EntityManagerUtil;

public class SpecialiteRepository {

    public Specialite save(Specialite specialite) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(specialite);
            transaction.commit();

            return specialite;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<Specialite> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(Specialite.class, id)
            );
        } finally {
            em.close();
        }
    }

    public Optional<Specialite> findByNom(String nom) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            List<Specialite> specialites = em.createQuery(
                            "SELECT s FROM Specialite s "
                                    + "WHERE LOWER(s.nom) = LOWER(:nom)",
                            Specialite.class
                    )
                    .setParameter("nom", nom)
                    .setMaxResults(1)
                    .getResultList();

            return specialites.stream().findFirst();
        } finally {
            em.close();
        }
    }

    public List<Specialite> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM Specialite s ORDER BY s.nom ASC",
                    Specialite.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public Specialite update(Specialite specialite) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Specialite updatedSpecialite = em.merge(specialite);

            transaction.commit();

            return updatedSpecialite;
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

            Specialite specialite = em.find(Specialite.class, id);

            if (specialite != null) {
                em.remove(specialite);
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