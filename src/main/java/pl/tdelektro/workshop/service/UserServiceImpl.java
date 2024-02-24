package pl.tdelektro.workshop.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.exception.CarAlreadyRegisteredException;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.UserRepository;
import pl.tdelektro.workshop.security.SecurityConfig;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private CarRepository carRepository;
    private SecurityConfig securityConfig;


    @Override
    public User getUser(Long userId) throws UserNotFoundException {
        return unwrapUser(userId);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void deleteUser(Long userId) throws UserNotFoundException {
        userRepository.delete(unwrapUser(userId));
    }

    @Override
    public User updateUser(Long userId, User user) throws UserNotFoundException {
        User userToUpdate = unwrapUser(userId);
        userRepository.save(user);
        return null;
    }

    @Override
    public User findUserVin(Long userId) {
        return null;
    }

    @Override
    public void saveUser(User user) {


        UserDetails userToEnroll = org.springframework.security.core.userdetails.User.builder()
                .username(user.getUsername())
                        .password(user.getPassword())
                                .roles("USER")
                                        .build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(userToEnroll);
        userRepository.save(user);
    }

    @Override
    public void assignUserToTheCar(Long userId, Long carId) throws UserNotFoundException, CarNotFoundException {
        User user = unwrapUser(userId);
        Car car = unwrapCar(carId);
        if(car.getUser() == null) {
            car.setUser(user);
            carRepository.save(car);
        }else{
            throw new CarAlreadyRegisteredException(car.getUser().getUsername());
        }


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
