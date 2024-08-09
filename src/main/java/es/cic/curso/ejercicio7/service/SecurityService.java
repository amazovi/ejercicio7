package es.cic.curso.ejercicio7.service;

import es.cic.curso.ejercicio7.model.Animal;
import es.cic.curso.ejercicio7.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityService {

@Autowired
    private AnimalRepository animalRepository;

    /**
     * Verifica si el usuario autenticado es el propietario del animal con el ID dado.
     *
     * @param authentication La autenticación del usuario actual.
     * @param animalId El ID del animal a verificar.
     * @return true si el usuario es el propietario del animal, false en caso contrario.
     */
    public boolean isOwner(Authentication authentication, Long animalId) {
        // Obtener el animal por su ID
        Optional<Animal> animalOptional = animalRepository.findById(animalId);

        // Si el animal no se encuentra, se retorna false
        if (!animalOptional.isPresent()) {
            return false;
        }

        // Obtener el animal desde el Optional
        Animal animal = animalOptional.get();

        // Supongamos que el propietario del animal está identificado por el nombre de usuario
        // y que el campo "owner" del animal almacena el nombre del propietario.
        String propietario = animal.getOwner(); 
        String usuarioActual = authentication.getName();

        // Verificar si el usuario autenticado es el propietario
        return usuarioActual.equals(propietario);
    }
}
