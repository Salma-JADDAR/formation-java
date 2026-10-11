package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import org.telexpertise.entity.Specialiste;
import org.telexpertise.util.EntityManagerUtil;

import java.util.List;
import java.util.Optional;

public class SpecialisteRepository {

    public Specialiste save(Specialiste specialiste) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            if (specialiste.getId() == null) {
                em.persist(specialiste);
            } else {
                specialiste = em.merge(specialiste);
            }

            em.getTransaction().commit();

            return specialiste;

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    public Optional<Specialiste> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(Specialiste.class, id)
            );
        } finally {
            em.close();
        }
    }

    public List<Specialiste> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT s FROM Specialiste s",
                    Specialiste.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            Specialiste specialiste =
                    em.find(Specialiste.class, id);

            if (specialiste != null) {
                em.remove(specialiste);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}