package org.telexpertise.service;

import org.telexpertise.entity.Generaliste;
import org.telexpertise.exception.DuplicateResourceException;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.GeneralisteRepository;

import java.util.List;

public class GeneralisteService {

    private final GeneralisteRepository repository;

    public GeneralisteService() {
        this.repository = new GeneralisteRepository();
    }

    public Generaliste create(Generaliste generaliste) throws ValidationException, DuplicateResourceException {

        if (generaliste == null || generaliste.getEmail() == null || generaliste.getEmail().isBlank()) {
            throw new ValidationException("Email obligatoire.");
        }

        if (repository.findByEmail(generaliste.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Cet email existe déjà.");
        }

        return repository.save(generaliste);
    }

    public Generaliste findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Généraliste introuvable.")
                );
    }

    public Generaliste findByEmail(String email) throws ValidationException, ResourceNotFoundException {

        if (email == null || email.isBlank()) {
            throw new ValidationException("Email obligatoire.");
        }

        return repository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Généraliste introuvable.")
                );
    }

    public List<Generaliste> findAll() {
        return repository.findAll();
    }

    public Generaliste update(Generaliste generaliste) throws ValidationException, ResourceNotFoundException {

        if (generaliste == null || generaliste.getId() == null) {
            throw new ValidationException("Généraliste invalide.");
        }

        if (repository.findById(generaliste.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Généraliste introuvable.");
        }

        return repository.update(generaliste);
    }

    public void deleteById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Généraliste introuvable.");
        }

        repository.deleteById(id);
    }
}

