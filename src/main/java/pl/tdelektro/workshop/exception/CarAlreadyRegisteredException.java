package pl.tdelektro.workshop.exception;

public class CarAlreadyRegisteredException extends RuntimeException {
    public CarAlreadyRegisteredException(String userName) {
        super("Car already registered to user: " + userName);
    }
}
