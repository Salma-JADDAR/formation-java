package org.telexpertise.service;

import org.telexpertise.entity.Patient;
import org.telexpertise.exception.DuplicateResourceException;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.PatientRepository;

import java.util.List;
import java.util.Optional;

public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService() {
        this.patientRepository = new PatientRepository();
    }

    public Patient create(Patient patient) throws ValidationException, DuplicateResourceException {

        validatePatient(patient);

        Optional<Patient> existingPatient =
                patientRepository.findByNumeroSecuriteSociale(
                        patient.getNumeroSecuriteSociale()
                );

        if (existingPatient.isPresent()) {
            throw new DuplicateResourceException("Ce numéro de sécurité sociale existe déjà.");
        }

        return patientRepository.save(patient);
    }

    public Patient findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("L'identifiant doit être positif.");
        }

        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient introuvable."
                        )
                );
    }

    public Patient findByNumeroSecuriteSociale(String numero) throws ValidationException, ResourceNotFoundException {

        if (numero == null || numero.isBlank()) {
            throw new ValidationException("Le numéro de sécurité sociale est obligatoire.");
        }

        return patientRepository.findByNumeroSecuriteSociale(numero.trim())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Patient introuvable."
                        )
                );
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(Patient patient) throws ValidationException, ResourceNotFoundException, DuplicateResourceException {

        validatePatient(patient);

        if (patient.getId() == null || patient.getId() <= 0) {
            throw new ValidationException("L'identifiant du patient est obligatoire.");
        }

        Patient existingPatient = patientRepository.findById(patient.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Patient introuvable.")
                );

        Optional<Patient> patientWithSameNumero =
                patientRepository.findByNumeroSecuriteSociale(
                        patient.getNumeroSecuriteSociale()
                );

        if (patientWithSameNumero.isPresent()
                && !patientWithSameNumero.get().getId()
                .equals(existingPatient.getId())) {
            throw new DuplicateResourceException("Ce numéro de sécurité sociale est déjà utilisé.");
        }

        return patientRepository.update(patient);
    }

    private void validatePatient(Patient patient)
            throws ValidationException {

        if (patient == null) {
            throw new ValidationException("Les informations du patient sont obligatoires.");
        }

        if (patient.getNom() == null || patient.getNom().isBlank()) {
            throw new ValidationException("Le nom est obligatoire.");
        }

        if (patient.getPrenom() == null || patient.getPrenom().isBlank()) {
            throw new ValidationException("Le prénom est obligatoire.");
        }

        if (patient.getNumeroSecuriteSociale() == null || patient.getNumeroSecuriteSociale().isBlank()) {
            throw new ValidationException("Le numéro de sécurité sociale est obligatoire.");
        }
    }
}

