package es.cic.curso.ejercicio7.service;

import es.cic.curso.ejercicio7.model.Animal;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "animal-topic", groupId = "animal-group")
    public void consume(Animal animal) {
        System.out.println("Consumed message: " + animal.getName() + ", " + animal.getType());
    }
}