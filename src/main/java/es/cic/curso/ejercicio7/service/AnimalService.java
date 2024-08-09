package es.cic.curso.ejercicio7.service;

import es.cic.curso.ejercicio7.model.Animal;
import es.cic.curso.ejercicio7.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SecurityService securityService;

    /**
     * Lista todos los animales.
     * Este método es accesible para todos los usuarios autenticados.
     *
     * @return Lista de animales.
     */
    @PreAuthorize("isAuthenticated()")
    public List<Animal> listAnimals() {
        return animalRepository.findAll();
    }

    /**
     * Encuentra un animal por su ID.
     * Este método es accesible solo por el propietario del animal o por un administrador.
     *
     * @param id El ID del animal.
     * @return Un Optional con el animal, si existe.
     */
    @PreAuthorize("hasRole('ADMIN') or @ownershipService.isOwner(authentication, #id)")
    public Optional<Animal> findAnimalById(Long id) {
        return animalRepository.findById(id);
    }

    /**
     * Guarda un animal en el repositorio.
     * Este método es accesible para todos los usuarios autenticados.
     *
     * @param animal El animal a guardar.
     * @return El animal guardado.
     */
    @PreAuthorize("isAuthenticated()")
    public Animal saveAnimal(Animal animal) {
        return animalRepository.save(animal);
    }

    /**
     * Elimina un animal por su ID.
     * Este método es accesible solo por el propietario del animal o por un administrador.
     *
     * @param id El ID del animal a eliminar.
     */
    @PreAuthorize("hasRole('ADMIN') or @ownershipService.isOwner(authentication, #id)")
    public void deleteAnimal(Long id) {
        if (!animalRepository.existsById(id)) {
            throw new IllegalArgumentException("El animal con ID " + id + " no existe.");
        }
        animalRepository.deleteById(id);
    }
}
