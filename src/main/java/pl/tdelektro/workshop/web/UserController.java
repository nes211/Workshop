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

    //All mappings only for ADMIN, expect POST /add and GET /email/{userEmail}

    //Retrieve user with userID from database
    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    //User retrieve his data from database after registration and successful login
    @GetMapping("/email/{userEmail}")
    public ResponseEntity<User> getUserData(@PathVariable String userEmail) throws UserNotFoundException {
        return new ResponseEntity<>(userService.getUserByEmail(userEmail), HttpStatus.OK);
    }

    //Return all users from database
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    //User can add new user to database
    @PostMapping("/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //Assign user with car
    @PostMapping("/{userId}/register/{carId}")
    public ResponseEntity<HttpStatus> assignUserToCar(@PathVariable Long userId, @PathVariable Long carId) throws UserNotFoundException, CarNotFoundException {
        userService.assignUserToTheCar(userId, carId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Delete user by userId
    @DeleteMapping("delete/{userId}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long userId) throws UserNotFoundException {
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Update user with userId from input JSON parsed to User
    @PutMapping("update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestBody User user) throws UserNotFoundException {
        return new ResponseEntity<>(userService.updateUser(userId, user), HttpStatus.OK);
    }

}
