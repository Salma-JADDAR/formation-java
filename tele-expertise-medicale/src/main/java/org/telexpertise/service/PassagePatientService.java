
package org.telexpertise.service;

import org.telexpertise.entity.PassagePatient;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.PassagePatientRepository;

import java.time.LocalDate;
import java.util.List;

public class PassagePatientService {

    private final PassagePatientRepository passagePatientRepository;

    public PassagePatientService() {
        this.passagePatientRepository = new PassagePatientRepository();
    }

    public PassagePatient create(PassagePatient passage) throws ValidationException {

        if (passage == null || passage.getPatient() == null
                || passage.getPatient().getId() == null) {
            throw new ValidationException("Les informations du passage patient sont obligatoires.");
        }

        return passagePatientRepository.save(passage);
    }

    public PassagePatient findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return passagePatientRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Passage patient introuvable.")
                );
    }

    public List<PassagePatient> findAll() {
        return passagePatientRepository.findAll();
    }

    public List<PassagePatient> findByDate(LocalDate date) throws ValidationException {

        if (date == null) {
            throw new ValidationException("La date est obligatoire.");
        }

        return passagePatientRepository.findByDate(date);
    }

    public List<PassagePatient> findByStatut(String statut) throws ValidationException {

        if (statut == null || statut.isBlank()) {
            throw new ValidationException("Le statut est obligatoire.");
        }

        return passagePatientRepository.findByStatut(statut);
    }

    public PassagePatient update(PassagePatient passage) throws ValidationException, ResourceNotFoundException {

        if (passage == null || passage.getId() == null) {
            throw new ValidationException("Passage patient invalide.");
        }

        if (passagePatientRepository.findById(passage.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Passage patient introuvable.");
        }

        return passagePatientRepository.update(passage);
    }
}