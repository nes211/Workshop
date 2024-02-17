package pl.tdelektro.workshop.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserServiceImpl userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) throws UserNotFoundException {
      return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Post mapping for register user with car only by ADMIN
    @PostMapping("/{userId}/register/{carId}")
    public ResponseEntity<HttpStatus> registerUserToCar(@PathVariable Long userId, @PathVariable Long carId)
            throws UserNotFoundException, CarNotFoundException {
        userService.registerUserToCar(userId, carId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("update/{userId}")
    public ResponseEntity<User>updateUser(@PathVariable Long userId, @RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }

}
