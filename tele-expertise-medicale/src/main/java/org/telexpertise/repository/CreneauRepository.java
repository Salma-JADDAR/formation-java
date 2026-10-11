
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.Creneau;
import org.telexpertise.enums.StatutCreneau;
import org.telexpertise.util.EntityManagerUtil;

public class CreneauRepository {

    public Creneau save(Creneau creneau) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(creneau);
            transaction.commit();
            return creneau;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<Creneau> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(em.find(Creneau.class, id));
        } finally {
            em.close();
        }
    }

    public List<Creneau> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT c FROM Creneau c "
                            + "ORDER BY c.dateHeureDebut ASC",
                    Creneau.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Creneau> findBySpecialisteId(Long specialisteId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT c FROM Creneau c "
                                    + "WHERE c.specialiste.id = :specialisteId "
                                    + "ORDER BY c.dateHeureDebut ASC",
                            Creneau.class
                    )
                    .setParameter("specialisteId", specialisteId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Creneau> findDisponiblesBySpecialisteId(
            Long specialisteId, LocalDateTime dateHeure) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT c FROM Creneau c "
                                    + "WHERE c.specialiste.id = :specialisteId "
                                    + "AND c.statut = :statut "
                                    + "AND c.dateHeureDebut >= :dateHeure "
                                    + "ORDER BY c.dateHeureDebut ASC",
                            Creneau.class
                    )
                    .setParameter("specialisteId", specialisteId)
                    .setParameter("statut", StatutCreneau.DISPONIBLE)
                    .setParameter("dateHeure", dateHeure)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<Creneau> findDisponiblesBySpecialiteId(
            Long specialiteId, LocalDateTime dateHeure) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT c FROM Creneau c "
                                    + "WHERE c.specialiste.specialite.id = :specialiteId "
                                    + "AND c.statut = :statut "
                                    + "AND c.dateHeureDebut >= :dateHeure "
                                    + "ORDER BY c.dateHeureDebut ASC",
                            Creneau.class
                    )
                    .setParameter("specialiteId", specialiteId)
                    .setParameter("statut", StatutCreneau.DISPONIBLE)
                    .setParameter("dateHeure", dateHeure)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Creneau update(Creneau creneau) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            Creneau updated = em.merge(creneau);
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

            Creneau creneau = em.find(Creneau.class, id);

            if (creneau != null) {
                em.remove(creneau);
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