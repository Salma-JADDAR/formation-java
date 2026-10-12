package org.telexpertise.service;

import org.telexpertise.entity.Creneau;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.CreneauRepository;

import java.time.LocalDateTime;
import java.util.List;

public class CreneauService {

    private final CreneauRepository repository;

    public CreneauService() {
        this.repository = new CreneauRepository();
    }

    public Creneau create(Creneau creneau) throws ValidationException {

        if (creneau == null || creneau.getSpecialiste() == null) {
            throw new ValidationException("Les informations du créneau sont obligatoires.");
        }

        return repository.save(creneau);
    }

    public Creneau findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Créneau introuvable.")
                );
    }

    public List<Creneau> findAll() {
        return repository.findAll();
    }

    public List<Creneau> findBySpecialisteId(Long specialisteId) throws ValidationException {

        if (specialisteId == null || specialisteId <= 0) {
            throw new ValidationException("Identifiant du spécialiste invalide.");
        }

        return repository.findBySpecialisteId(specialisteId);
    }

    public List<Creneau> findDisponiblesBySpecialisteId(Long specialisteId, LocalDateTime dateHeure) throws ValidationException {

        if (specialisteId == null || specialisteId <= 0 || dateHeure == null) {
            throw new ValidationException("Le spécialiste et la date sont obligatoires.");
        }

        return repository.findDisponiblesBySpecialisteId(specialisteId, dateHeure);
    }

    public List<Creneau> findDisponiblesBySpecialiteId(Long specialiteId, LocalDateTime dateHeure) throws ValidationException {

        if (specialiteId == null || specialiteId <= 0 || dateHeure == null) {
            throw new ValidationException("La spécialité et la date sont obligatoires.");
        }

        return repository.findDisponiblesBySpecialiteId(specialiteId, dateHeure);
    }

    public Creneau update(Creneau creneau) throws ValidationException, ResourceNotFoundException {

        if (creneau == null || creneau.getId() == null) {
            throw new ValidationException("Créneau invalide.");
        }

        if (repository.findById(creneau.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Créneau introuvable.");
        }

        return repository.update(creneau);
    }

    public void deleteById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Créneau introuvable.");
        }

        repository.deleteById(id);
    }
}

