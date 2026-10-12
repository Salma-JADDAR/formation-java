package org.telexpertise.service;

import org.telexpertise.entity.Prescription;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.PrescriptionRepository;

import java.util.List;

public class PrescriptionService {

    private final PrescriptionRepository repository;

    public PrescriptionService() {
        this.repository = new PrescriptionRepository();
    }

    public Prescription create(Prescription prescription) throws ValidationException {

        if (prescription == null || prescription.getConsultation() == null) {
            throw new ValidationException("Les informations de la prescription sont obligatoires.");
        }

        return repository.save(prescription);
    }

    public Prescription findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prescription introuvable.")
                );
    }

    public List<Prescription> findAll() {
        return repository.findAll();
    }

    public Prescription findByConsultationId(Long consultationId) throws ValidationException, ResourceNotFoundException {

        if (consultationId == null || consultationId <= 0) {
            throw new ValidationException("Identifiant de la consultation invalide.");
        }

        return repository.findByConsultationId(consultationId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Prescription introuvable pour cette consultation.")
                );
    }

    public Prescription update(Prescription prescription) throws ValidationException, ResourceNotFoundException {

        if (prescription == null || prescription.getId() == null) {
            throw new ValidationException("Prescription invalide.");
        }

        if (repository.findById(prescription.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Prescription introuvable.");
        }

        return repository.update(prescription);
    }

    public boolean deleteById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Prescription introuvable.");
        }

        return repository.deleteById(id);
    }
}

