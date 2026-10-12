
package org.telexpertise.service;

import org.telexpertise.entity.SigneVital;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.SigneVitalRepository;

import java.util.List;

public class SigneVitalService {

    private final SigneVitalRepository signeVitalRepository;

    public SigneVitalService() {
        this.signeVitalRepository = new SigneVitalRepository();
    }

    public SigneVital create(SigneVital signeVital) throws ValidationException {

        if (signeVital == null || signeVital.getPatient() == null
                || signeVital.getPatient().getId() == null) {
            throw new ValidationException("Les informations du signe vital sont obligatoires.");
        }

        return signeVitalRepository.save(signeVital);
    }

    public SigneVital findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return signeVitalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Signe vital introuvable.")
                );
    }

    public List<SigneVital> findByPatientId(Long patientId)
            throws ValidationException {

        if (patientId == null || patientId <= 0) {
            throw new ValidationException("Identifiant du patient invalide.");
        }

        return signeVitalRepository.findByPatientId(patientId);
    }

    public List<SigneVital> findAll() {
        return signeVitalRepository.findAll();
    }

    public SigneVital update(SigneVital signeVital) throws ValidationException, ResourceNotFoundException {

        if (signeVital == null || signeVital.getId() == null) {
            throw new ValidationException("Signe vital invalide.");
        }

        if (signeVitalRepository.findById(signeVital.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Signe vital introuvable.");
        }

        return signeVitalRepository.update(signeVital);
    }
}