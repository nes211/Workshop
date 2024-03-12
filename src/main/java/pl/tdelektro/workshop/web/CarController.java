package pl.tdelektro.workshop.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.service.CarServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    CarServiceImpl carService;

    //
    //All mappings only for ADMIN
    //

    //List of all to-do tasks assigned to all users
    @GetMapping("/{carId}")
    ResponseEntity<Car> getCarFromRequest(@PathVariable Long carId) throws CarNotFoundException {
        return new ResponseEntity<>(carService.getCar(carId), HttpStatus.OK);
    }

    //Retrieve all cars assigned to user
    @GetMapping("/{userId}/all")
    ResponseEntity<List<Car>> getAllUserCars(@PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(carService.getAllUserCars(userId), HttpStatus.OK);
    }

    //Add new car to database
    @PostMapping("/add")
    ResponseEntity<HttpStatus> addCar(@RequestBody Car car) {
        carService.addCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Delete car from database
    @DeleteMapping("/delete/{carId}")
    ResponseEntity<HttpStatus> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Update car data from database by parse JSON
    @PutMapping("/update/{carId}")
    ResponseEntity<HttpStatus> updateCar(@PathVariable Long carId, @RequestBody Car car) throws CarNotFoundException {
        carService.updateCar(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Retrieve all cars from database
    @GetMapping("/all")
    ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

}
