package es.cic.curso.ejercicio7.service;

import java.util.Arrays;
import java.util.List;

import es.cic.curso.ejercicio7.model.Animal;

public class AnimalService {

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