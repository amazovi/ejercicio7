package es.cic.curso.ejercicio7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.ejercicio7.model.Animal;
import es.cic.curso.ejercicio7.repository.AnimalRepository;
import es.cic.curso.ejercicio7.service.SecurityService;

import java.security.Principal;
import org.springframework.security.core.Authentication;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SecurityService securityService;

    @GetMapping("/list")
    public List<Animal> listAnimals() {
        Animal dog = new Animal();
        dog.setName("Dog");
        dog.setType("Mammal");

        Animal cat = new Animal();
        cat.setName("Cat");
        cat.setType("Mammal");

        Animal rabbit = new Animal();
        rabbit.setName("Rabbit");
        rabbit.setType("Mammal");

        return Arrays.asList(dog, cat, rabbit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Animal> getAnimal(@PathVariable Long id, Principal principal) {
        Optional<Animal> animalOpt = animalRepository.findById(id);
        if (animalOpt.isPresent()) {
            Animal animal = animalOpt.get();
            if (!securityService.isOwner((Authentication) principal, id)) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
            return ResponseEntity.ok(animal);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}