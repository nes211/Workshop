package pl.tdelektro.workshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Override
    public User getUser(Long userId) throws UserNotFoundException {
        User user = unwrapUser(userId);
        return user;
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
    public void enrollUserToCar(Long userId, String vinNumber) throws UserNotFoundException {
        User user = unwrapUser(userId);


    }

    private User unwrapUser(Long userId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
