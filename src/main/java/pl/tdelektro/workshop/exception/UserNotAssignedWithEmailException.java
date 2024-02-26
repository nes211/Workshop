package pl.tdelektro.workshop.exception;

public class UserNotAssignedWithEmailException extends RuntimeException {
    public UserNotAssignedWithEmailException(String loggedUserEmail, String email) {
        super("Logged user email address " +loggedUserEmail+ " not assigned with requested email address " + email);
    }
}
