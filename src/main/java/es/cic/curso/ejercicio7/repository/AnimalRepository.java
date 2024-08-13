package es.cic.curso.ejercicio7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.cic.curso.ejercicio7.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

}


