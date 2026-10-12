
package org.telexpertise.service;

import org.telexpertise.entity.Infirmier;
import org.telexpertise.exception.DuplicateResourceException;
import org.telexpertise.exception.ValidationException;
import org.telexpertise.repository.UserRepository;

public class InfirmierService {

    private final UserRepository userRepository;

    public InfirmierService() {
        this.userRepository = new UserRepository();
    }

    public Infirmier create(Infirmier infirmier) throws ValidationException, DuplicateResourceException {

        if (infirmier == null || infirmier.getEmail() == null || infirmier.getEmail().isBlank()) {
            throw new ValidationException("Les informations de l'infirmier sont obligatoires.");
        }

        if (userRepository.findByEmail(infirmier.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Cet email existe déjà.");
        }

        return (Infirmier) userRepository.save(infirmier);
    }
}