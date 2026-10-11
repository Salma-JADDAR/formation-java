
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.DossierMedical;
import org.telexpertise.util.EntityManagerUtil;

public class DossierMedicalRepository {

    public DossierMedical save(DossierMedical dossierMedical) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(dossierMedical);
            transaction.commit();

            return dossierMedical;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<DossierMedical> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(DossierMedical.class, id)
            );
        } finally {
            em.close();
        }
    }

    public Optional<DossierMedical> findByPatientId(Long patientId) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            List<DossierMedical> dossiers = em.createQuery(
                            "SELECT d FROM DossierMedical d "
                                    + "WHERE d.patient.id = :patientId",
                            DossierMedical.class
                    )
                    .setParameter("patientId", patientId)
                    .setMaxResults(1)
                    .getResultList();

            return dossiers.stream().findFirst();
        } finally {
            em.close();
        }
    }

    public List<DossierMedical> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT d FROM DossierMedical d "
                            + "ORDER BY d.dateCreation DESC",
                    DossierMedical.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public DossierMedical update(DossierMedical dossierMedical) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            DossierMedical updatedDossier = em.merge(dossierMedical);

            transaction.commit();

            return updatedDossier;
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

            DossierMedical dossier = em.find(DossierMedical.class, id);

            if (dossier != null) {
                em.remove(dossier);
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