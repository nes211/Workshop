package pl.tdelektro.workshop.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.UserRepository;
import pl.tdelektro.workshop.service.CarServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    CarServiceImpl carService;
    @GetMapping("/{vinNumber}")
    public ResponseEntity<Car>getCarFromRequest(@PathVariable String vinNumber){
        return new ResponseEntity<>(carService.getCar(vinNumber),HttpStatus.OK);
    }

    @GetMapping("/{userId}/all")
    public ResponseEntity<List<Car>>getAllUserCars(@PathVariable Long userId) throws UserNotFoundException {


        return new ResponseEntity<>(carService.getAllUserCars(userId), HttpStatus.OK);
    }

    void addCar(String vin);
    void deleteCar(String vin);
    void updateCar(String vin);











}
