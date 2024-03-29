package pl.tdelektro.workshop.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long userId) {
        super("User with id: " + userId + " does not exist in repository");
    }
}
