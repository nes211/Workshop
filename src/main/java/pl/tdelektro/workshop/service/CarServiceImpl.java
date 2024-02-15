package pl.tdelektro.workshop.service;

import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.pojo.Car;

import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    @Override
    public Car getCar(String vin) {
        return null;
    }

    @Override
    public List<Car> getAllCars(String email) {
        return null;
    }

    @Override
    public void addCar(String vin) {

    }

    @Override
    public void deleteCar(String vin) {

    }

    @Override
    public void updateCar(String vin) {

    }
}
