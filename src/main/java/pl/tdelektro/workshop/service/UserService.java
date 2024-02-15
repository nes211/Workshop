package pl.tdelektro.workshop.service;

import org.springframework.http.ResponseEntity;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.User;

import java.util.List;

public interface UserService {

    User getUser(Long userId) throws UserNotFoundException;
    List<User>getAllUsers();
    void deleteUser(int userId);
    User updateUser(int userId);
    User findUserVin(int userId);
    void saveUser(User user);

    void registerUserToCar(Long userId, String vinNumber) throws UserNotFoundException, CarNotFoundException;
}