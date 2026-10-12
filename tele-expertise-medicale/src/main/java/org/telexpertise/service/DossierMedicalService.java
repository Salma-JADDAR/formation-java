
package org.telexpertise.service;

import org.telexpertise.entity.DossierMedical;
import org.telexpertise.exception.DuplicateResourceException;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.DossierMedicalRepository;

import java.util.List;

public class DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;

    public DossierMedicalService() {
        this.dossierMedicalRepository = new DossierMedicalRepository();
    }

    public DossierMedical create(DossierMedical dossier) throws ValidationException, DuplicateResourceException {

        if (dossier == null || dossier.getPatient() == null
                || dossier.getPatient().getId() == null) {
            throw new ValidationException("Les informations du dossier sont obligatoires.");
        }

        if (dossierMedicalRepository
                .findByPatientId(dossier.getPatient().getId()).isPresent()) {
            throw new DuplicateResourceException("Ce patient possède déjà un dossier médical.");
        }

        return dossierMedicalRepository.save(dossier);
    }

    public DossierMedical findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return dossierMedicalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dossier médical introuvable.")
                );
    }

    public DossierMedical findByPatientId(Long patientId) throws ValidationException, ResourceNotFoundException {

        if (patientId == null || patientId <= 0) {
            throw new ValidationException("Identifiant du patient invalide.");
        }

        return dossierMedicalRepository.findByPatientId(patientId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Dossier médical introuvable.")
                );
    }

    public List<DossierMedical> findAll() {
        return dossierMedicalRepository.findAll();
    }

    public DossierMedical update(DossierMedical dossier) throws ValidationException, ResourceNotFoundException {

        if (dossier == null || dossier.getId() == null) {
            throw new ValidationException("Dossier invalide.");
        }

        if (dossierMedicalRepository.findById(dossier.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Dossier médical introuvable.");
        }

        return dossierMedicalRepository.update(dossier);
    }
}