
package org.telexpertise.service;

import org.telexpertise.entity.User;
import org.telexpertise.exception.DuplicateResourceException;
import org.telexpertise.exception.ResourceNotFoundException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.UserRepository;

import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }

    public User create(User user) throws ValidationException, DuplicateResourceException {

        if (user == null || user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ValidationException("Email obligatoire.");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Cet email existe déjà.");
        }

        return userRepository.save(user);
    }

    public User findById(Long id) throws ValidationException, ResourceNotFoundException {

        if (id == null || id <= 0) {
            throw new ValidationException("Identifiant invalide.");
        }

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Utilisateur introuvable."
                        )
                );
    }

    public User findByEmail(String email) throws ValidationException, ResourceNotFoundException {

        if (email == null || email.isBlank()) {
            throw new ValidationException("Email obligatoire.");
        }

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Utilisateur introuvable.")
                );
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User update(User user) throws ValidationException, ResourceNotFoundException {

        if (user == null || user.getId() == null) {
            throw new ValidationException("Utilisateur invalide.");
        }

        if (userRepository.findById(user.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Utilisateur introuvable.");
        }

        return userRepository.update(user);
    }
}