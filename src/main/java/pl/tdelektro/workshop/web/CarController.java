package pl.tdelektro.workshop.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
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

    @GetMapping("/{carId}")
    public ResponseEntity<Car> getCarFromRequest(@PathVariable Long carId) throws CarNotFoundException {
        return new ResponseEntity<>(carService.getCar(carId), HttpStatus.OK);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<Car>> getAllUserCars(@PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(carService.getAllUserCars(userId), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addCar(@RequestBody Car car) {
        carService.addCar(car);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{carId}")
    public ResponseEntity<HttpStatus> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{carId}")
    public ResponseEntity<HttpStatus> updateCar(@PathVariable Long carId, @RequestBody Car car) throws CarNotFoundException {
        carService.updateCar(car);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {

        return new ResponseEntity<>(carService.getAllCars(), HttpStatus.OK);
    }

}
