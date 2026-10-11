
package org.telexpertise.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import org.telexpertise.entity.Patient;
import org.telexpertise.util.EntityManagerUtil;

public class PatientRepository {

    public Patient save(Patient patient) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(patient);
            transaction.commit();

            return patient;
        } catch (RuntimeException exception) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw exception;
        } finally {
            em.close();
        }
    }

    public Optional<Patient> findById(Long id) {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return Optional.ofNullable(
                    em.find(Patient.class, id)
            );
        } finally {
            em.close();
        }
    }

    public Optional<Patient> findByNumeroSecuriteSociale(
            String numeroSecuriteSociale) {

        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            List<Patient> patients = em.createQuery(
                            "SELECT p FROM Patient p "
                                    + "WHERE p.numeroSecuriteSociale = :numero",
                            Patient.class
                    )
                    .setParameter("numero", numeroSecuriteSociale)
                    .setMaxResults(1)
                    .getResultList();

            return patients.stream().findFirst();
        } finally {
            em.close();
        }
    }

    public List<Patient> findAll() {
        EntityManager em = EntityManagerUtil.getEntityManager();

        try {
            return em.createQuery(
                    "SELECT p FROM Patient p ORDER BY p.nom ASC, p.prenom ASC",
                    Patient.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    public Patient update(Patient patient) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            Patient updatedPatient = em.merge(patient);

            transaction.commit();

            return updatedPatient;
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

            Patient patient = em.find(Patient.class, id);

            if (patient != null) {
                em.remove(patient);
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