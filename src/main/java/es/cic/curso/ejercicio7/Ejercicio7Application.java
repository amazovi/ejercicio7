package es.cic.curso.ejercicio7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
@ComponentScan(basePackages = "es.cic.curso.ejercicio7")
public class Ejercicio7Application {

	public static void main(String[] args) {
		SpringApplication.run(Ejercicio7Application.class, args);
	}

}
