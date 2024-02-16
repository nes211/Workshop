package pl.tdelektro.workshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CarRepository carRepository;

    @Override
    public User getUser(Long userId) throws UserNotFoundException {
        return unwrapUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public User updateUser(int userId) {
        return null;
    }

    @Override
    public User findUserVin(int userId) {
        return null;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void registerUserToCar(Long userId, Long carId) throws UserNotFoundException, CarNotFoundException {
        User user = unwrapUser(userId);
        Car car = unwrapCar(carId);
        user.getCars().add(car);
        userRepository.save(user);
    }

    private User unwrapUser(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    private Car unwrapCar(Long carId) throws CarNotFoundException {
        Optional<Car> car = carRepository.findById(carId);
        if (car.isPresent()) {
            return car.get();
        } else {
            throw new CarNotFoundException(carId);
        }
    }
}
