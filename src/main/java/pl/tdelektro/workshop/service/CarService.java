package pl.tdelektro.workshop.service;

import pl.tdelektro.workshop.pojo.Car;

import java.util.List;

public interface CarService {

    Car getCar(String vin);
    List<Car> getAllCars(String email);
    void addCar(String vin);
    void deleteCar(String vin);
    void updateCar(String vin);

}
