package es.cic.curso.ejercicio7;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import es.cic.curso.ejercicio7.model.Animal;
import es.cic.curso.ejercicio7.repository.AnimalRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AnimalRepository animalRepository;

    private Animal testAnimal;

    @BeforeEach
    public void setup() {
        // Crear y guardar un animal de prueba
        testAnimal = new Animal();
        testAnimal.setName("Test Animal");
        testAnimal.setType("Test Type");
        animalRepository.save(testAnimal);
        System.out.println("Animal de prueba creado con ID: " + testAnimal.getId());
    }

    @Test
    @WithMockUser(username = "notOwner")
    public void whenNotOwnerTriesToReadAnimal_thenThrowException() throws Exception {
        System.out.println("Intentando acceder al animal con ID: " + testAnimal.getId() + " como usuario 'notOwner'");
        mockMvc.perform(get("/api/animals/" + testAnimal.getId()))
                .andExpect(status().isForbidden())
                .andDo(result -> {
                    System.out.println("Resultado de la solicitud: " + result.getResponse().getStatus());
                    if (result.getResponse().getStatus() == 403) {
                        System.out.println("Acceso denegado correctamente para el usuario 'notOwner'.");
                    } else {
                        System.out.println("Error: Se esperaba un estado 403 Forbidden.");
                    }
                });
    }
}