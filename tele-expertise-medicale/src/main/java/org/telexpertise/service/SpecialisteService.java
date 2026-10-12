
package org.telexpertise.service;

import org.telexpertise.entity.Specialiste;
import org.telexpertise.exception.DuplicateResourceException;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.SpecialisteRepository;

import java.util.List;

public class SpecialisteService {

    private final SpecialisteRepository repository = new SpecialisteRepository();

    public Specialiste create(Specialiste specialiste) throws ValidationException, DuplicateResourceException {

        if (specialiste == null || specialiste.getEmail() == null || specialiste.getEmail().isBlank()) {
            throw new ValidationException("Email obligatoire.");
        }

        if (repository.findByEmail(specialiste.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email déjà utilisé.");
        }

        return repository.save(specialiste);
    }

    public Specialiste findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Spécialiste introuvable.")
                );
    }

    public List<Specialiste> findAll() {
        return repository.findAll();
    }

    public Specialiste update(Specialiste specialiste) throws ValidationException, ResourceNotFoundException {

        if (specialiste == null || specialiste.getId() == null) {
            throw new ValidationException("Spécialiste invalide.");
        }

        if (repository.findById(specialiste.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Spécialiste introuvable.");
        }

        return repository.update(specialiste);
    }
}