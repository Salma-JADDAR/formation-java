
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.Generaliste;
import org.telexpertise.util.EntityManagerUtil;

public class GeneralisteRepository {

    public Generaliste save(Generaliste generaliste) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(generaliste);
            transaction.commit();

            return generaliste;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<Generaliste> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(Generaliste.class, id)
            );
        } finally {
            em.close();
        }
    }

    public Optional<Generaliste> findByEmail(String email) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            List<Generaliste> result = em.createQuery(
                            "SELECT g FROM Generaliste g "
                                    + "WHERE g.email = :email",
                            Generaliste.class
                    )
                    .setParameter("email", email)
                    .setMaxResults(1)
                    .getResultList();

            return result.stream().findFirst();
        } finally {
            em.close();
        }
    }

    public List<Generaliste> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT g FROM Generaliste g "
                            + "ORDER BY g.nom ASC, g.prenom ASC",
                    Generaliste.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public Generaliste update(Generaliste generaliste) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Generaliste updatedGeneraliste = em.merge(generaliste);

            transaction.commit();

            return updatedGeneraliste;
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

            Generaliste generaliste = em.find(Generaliste.class, id);

            if (generaliste != null) {
                em.remove(generaliste);
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