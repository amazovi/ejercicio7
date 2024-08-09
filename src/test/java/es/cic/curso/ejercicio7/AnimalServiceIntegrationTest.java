package es.cic.curso.ejercicio7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import es.cic.curso.ejercicio7.model.Animal;
import es.cic.curso.ejercicio7.repository.AnimalRepository;
import es.cic.curso.ejercicio7.service.AnimalService;

@SpringBootTest
public class AnimalServiceIntegrationTest {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private AnimalRepository animalRepository;

    @BeforeEach
    @Transactional
    public void setUp() {
        animalRepository.deleteAll();

        Animal dog = new Animal("Dog", "Mammal");
        Animal cat = new Animal("Cat", "Mammal");
        Animal rabbit = new Animal("Rabbit", "Mammal");

        animalRepository.save(dog);
        animalRepository.save(cat);
        animalRepository.save(rabbit);
    }

    @Test
    public void testListAnimals() {
        List<Animal> animals = animalService.listAnimals();
        assertNotNull(animals);
        assertEquals(3, animals.size());

        Animal dog = animals.get(0);
        assertEquals("Dog", dog.getName());
        assertEquals("Mammal", dog.getType());

        Animal cat = animals.get(1);
        assertEquals("Cat", cat.getName());
        assertEquals("Mammal", cat.getType());

        Animal rabbit = animals.get(2);
        assertEquals("Rabbit", rabbit.getName());
        assertEquals("Mammal", rabbit.getType());
    }
}