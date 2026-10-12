package org.telexpertise.service;

import org.telexpertise.entity.ActeMedical;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.ActeMedicalRepository;

import java.util.List;

public class ActeMedicalService {

    private final ActeMedicalRepository repository;

    public ActeMedicalService() {
        this.repository = new ActeMedicalRepository();
    }

    public ActeMedical create(ActeMedical acteMedical) throws ValidationException {

        if (acteMedical == null || acteMedical.getConsultation() == null) {
            throw new ValidationException("Les informations de l'acte médical sont obligatoires.");
        }

        return repository.save(acteMedical);
    }

    public ActeMedical findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Acte médical introuvable.")
                );
    }

    public List<ActeMedical> findAll() {
        return repository.findAll();
    }

    public List<ActeMedical> findByConsultationId(Long consultationId) throws ValidationException {

        if (consultationId == null || consultationId <= 0) {
            throw new ValidationException("Identifiant de la consultation invalide.");
        }

        return repository.findByConsultationId(consultationId);
    }

    public ActeMedical update(ActeMedical acteMedical) throws ValidationException, ResourceNotFoundException {

        if (acteMedical == null || acteMedical.getId() == null) {
            throw new ValidationException("Acte médical invalide.");
        }

        if (repository.findById(acteMedical.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Acte médical introuvable.");
        }

        return repository.update(acteMedical);
    }

    public boolean deleteById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Acte médical introuvable.");
        }

        return repository.deleteById(id);
    }
}

