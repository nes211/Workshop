package pl.tdelektro.workshop.service;

import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.pojo.Car;

import java.util.List;

public interface CarService {

    Car getCar(Long carId) throws CarNotFoundException;
    List<Car> getAllUserCars(Long userId);
    void addCar(Car car);
    void deleteCar(Long carId);
    void updateCar(Car car);

    List<Car> getAllCars();
}
