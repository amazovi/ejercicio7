package es.cic.curso.ejercicio7.schedule;

import es.cic.curso.ejercicio7.model.Animal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class ScheduledTasks {

    @Autowired
    private KafkaTemplate<String, Animal> kafkaTemplate;

    private static final String TOPIC = "animal-topic";

    @Scheduled(fixedRate = 5000)
    public void sendMessage() {
        Animal animal = new Animal("ScheduledAnimal", "ScheduledType");
        kafkaTemplate.send(TOPIC, animal);
        System.out.println("Scheduled message sent: " + animal.getName() + ", " + animal.getType());
    }
}