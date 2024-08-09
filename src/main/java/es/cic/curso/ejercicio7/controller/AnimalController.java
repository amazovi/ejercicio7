package es.cic.curso.ejercicio7.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.cic.curso.ejercicio7.model.Animal;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/animals")
public class AnimalController {

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
}