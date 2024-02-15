package pl.tdelektro.workshop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.tdelektro.workshop.pojo.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
}
