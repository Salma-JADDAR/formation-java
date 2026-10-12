package org.telexpertise.service;

import org.telexpertise.entity.Consultation;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.ConsultationRepository;

import java.time.LocalDate;
import java.util.List;

public class ConsultationService {

    private final ConsultationRepository repository;

    public ConsultationService() {
        this.repository = new ConsultationRepository();
    }

    public Consultation create(Consultation consultation) throws ValidationException {

        if (consultation == null || consultation.getPatient() == null || consultation.getGeneraliste() == null) {
            throw new ValidationException("Les informations de la consultation sont obligatoires.");
        }

        return repository.save(consultation);
    }

    public Consultation findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consultation introuvable.")
                );
    }

    public List<Consultation> findAll() {
        return repository.findAll();
    }

    public List<Consultation> findByPatientId(Long patientId) throws ValidationException {

        if (patientId == null || patientId <= 0) {
            throw new ValidationException("Identifiant du patient invalide.");
        }

        return repository.findByPatientId(patientId);
    }

    public List<Consultation> findByGeneralisteId(Long generalisteId) throws ValidationException {

        if (generalisteId == null || generalisteId <= 0) {
            throw new ValidationException("Identifiant du généraliste invalide.");
        }

        return repository.findByGeneralisteId(generalisteId);
    }

    public List<Consultation> findByDate(LocalDate date) throws ValidationException {

        if (date == null) {
            throw new ValidationException("La date est obligatoire.");
        }

        return repository.findByDate(date);
    }

    public Consultation update(Consultation consultation) throws ValidationException, ResourceNotFoundException {

        if (consultation == null || consultation.getId() == null) {
            throw new ValidationException("Consultation invalide.");
        }

        if (repository.findById(consultation.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Consultation introuvable.");
        }

        return repository.update(consultation);
    }

    public void deleteById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        if (repository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Consultation introuvable.");
        }

        repository.deleteById(id);
    }
}

