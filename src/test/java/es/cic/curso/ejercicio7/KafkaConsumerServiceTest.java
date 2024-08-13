package es.cic.curso.ejercicio7;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import es.cic.curso.ejercicio7.model.Animal;
import es.cic.curso.ejercicio7.service.KafkaConsumerService;

@SpringJUnitConfig
@EmbeddedKafka(partitions = 1, topics = { "animal-topic" })
public class KafkaConsumerServiceTest {

    @Mock
    private KafkaTemplate<String, Animal> kafkaTemplate;

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsumeMessage() {
        Animal animal = new Animal("TestAnimal", "TestType");
        kafkaConsumerService.consume(animal);
        verify(kafkaTemplate).send("animal-topic", animal);
    }
}