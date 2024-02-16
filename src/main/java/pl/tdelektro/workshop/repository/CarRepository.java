package pl.tdelektro.workshop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.tdelektro.workshop.pojo.Car;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends CrudRepository<Car, Long> {
    Optional<Car> findByVinNumber(String vinNumber);
    Optional<Car>findById(Long carId);

    List<Car> findByUser_Id(Long id);
}
