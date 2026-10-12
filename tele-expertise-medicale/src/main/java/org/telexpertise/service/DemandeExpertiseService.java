package org.telexpertise.service;

import org.telexpertise.entity.DemandeExpertise;
import org.telexpertise.enums.StatutExpertise;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.DemandeExpertiseRepository;

import java.util.List;

public class DemandeExpertiseService {

    private final DemandeExpertiseRepository repository;

    public DemandeExpertiseService() {
        this.repository = new DemandeExpertiseRepository();
    }

    public DemandeExpertise create(DemandeExpertise demande) throws ValidationException {

        if (demande == null|| demande.getConsultation() == null || demande.getSpecialiste() == null) {
            throw new ValidationException("Les informations de la demande d'expertise sont obligatoires.");
        }

        return repository.save(demande);
    }

    public DemandeExpertise findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Demande d'expertise introuvable.")
                );
    }

    public List<DemandeExpertise> findAll() {
        return repository.findAll();
    }

    public List<DemandeExpertise> findByConsultationId(Long consultationId) throws ValidationException {

        if (consultationId == null || consultationId <= 0) {
            throw new ValidationException("Identifiant de la consultation invalide.");
        }

        return repository.findByConsultationId(consultationId);
    }

    public List<DemandeExpertise> findBySpecialisteId(Long specialisteId) throws ValidationException {

        if (specialisteId == null || specialisteId <= 0) {
            throw new ValidationException("Identifiant du spécialiste invalide.");
        }

        return repository.findBySpecialisteId(specialisteId);
    }

    public List<DemandeExpertise> findByStatut(StatutExpertise statut) throws ValidationException {

        if (statut == null) {
            throw new ValidationException("Le statut est obligatoire.");
        }

        return repository.findByStatut(statut);
    }

    public DemandeExpertise update(DemandeExpertise demande) throws ValidationException, ResourceNotFoundException {

        if (demande == null || demande.getId() == null) {
            throw new ValidationException("Demande d'expertise invalide."
            );
        }

        if (repository.findById(demande.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Demande d'expertise introuvable.");
        }

        return repository.update(demande);
    }

    public boolean deleteById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Demande d'expertise introuvable.");
        }

        return repository.deleteById(id);
    }
}

