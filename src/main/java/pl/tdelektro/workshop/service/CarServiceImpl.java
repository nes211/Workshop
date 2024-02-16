package pl.tdelektro.workshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService{

    CarRepository carRepository;

    @Override
    public Car getCar(Long carId) throws CarNotFoundException {
        return unwrapCar(carId);
    }

    @Override
    public List<Car> getAllUserCars(Long userId) {
        return carRepository.findByUser_Id(userId);
    }

    @Override
    public void addCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(Long carId) {
        carRepository.deleteById(carId);
    }

    @Override
    public void updateCar(Car car) {
        carRepository.save(car);
    }

    private Car unwrapCar(Long carId) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(carId);
        if(car.isPresent()){
            return car.get();
        }else{
            throw new CarNotFoundException(carId);
        }
    }
}
