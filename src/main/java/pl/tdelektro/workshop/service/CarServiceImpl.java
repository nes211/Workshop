package pl.tdelektro.workshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.TaskNotFoundException;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    CarRepository carRepository;
    TaskRepository taskRepository;

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
        Task initialTask = unwrapTask(1L);
        car.setTasks(Arrays.asList(initialTask));
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

    @Override
    public List<Car> getAllCars() {
        return (List<Car>) carRepository.findAll();
    }

    private Car unwrapCar(Long carId) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            return car.get();
        } else {
            return null;
        }
    }

    private Task unwrapTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);
        if (task.isPresent()) {
            return task.get();
        } else {
            throw new TaskNotFoundException(taskId);
        }

    }

}
