package pl.tdelektro.workshop.service;

import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.User;

import java.util.List;

public interface UserService {

    User getUser(Long userId) throws UserNotFoundException;

    List<User> getAllUsers();

    void deleteUser(Long userId) throws UserNotFoundException;

    User updateUser(Long userId, User user) throws UserNotFoundException;

    User findUserVin(Long userId);

    void saveUser(User user);

    void registerUserToCar(Long userId, Long carId) throws UserNotFoundException, CarNotFoundException;
}