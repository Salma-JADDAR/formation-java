
package org.telexpertise.service;

import org.telexpertise.entity.Specialite;
import org.telexpertise.exception.DuplicateResourceException;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.SpecialiteRepository;

import java.util.List;

public class SpecialiteService {

    private final SpecialiteRepository specialiteRepository;

    public SpecialiteService() {
        this.specialiteRepository = new SpecialiteRepository();
    }

    public Specialite create(Specialite specialite) throws ValidationException, DuplicateResourceException {

        if (specialite == null || specialite.getNom() == null
                || specialite.getNom().isBlank()) {
            throw new ValidationException("Le nom de la spécialité est obligatoire.");
        }

        if (specialiteRepository.findByNom(specialite.getNom()).isPresent()) {
            throw new DuplicateResourceException("Cette spécialité existe déjà.");
        }

        return specialiteRepository.save(specialite);
    }

    public Specialite findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return specialiteRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Spécialité introuvable.")
                );
    }

    public List<Specialite> findAll() {
        return specialiteRepository.findAll();
    }

    public Specialite update(Specialite specialite) throws ValidationException, ResourceNotFoundException {

        if (specialite == null || specialite.getId() == null) {
            throw new ValidationException("Spécialité invalide.");
        }

        if (specialiteRepository.findById(specialite.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Spécialité introuvable.");
        }

        return specialiteRepository.update(specialite);
    }
}