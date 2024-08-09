package es.cic.curso.ejercicio7;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import es.cic.curso.ejercicio7.model.Animal; // Add this import statement
import es.cic.curso.ejercicio7.service.AnimalService;

@SpringBootTest
public class AnimalServiceIntegrationTest {

    @Autowired
    private AnimalService animalService;

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