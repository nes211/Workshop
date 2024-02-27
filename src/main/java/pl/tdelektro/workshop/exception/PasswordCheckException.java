package pl.tdelektro.workshop.exception;

import jakarta.validation.ValidationException;

public class PasswordCheckException extends ValidationException{
    public PasswordCheckException(){
        super("Password invalid. Password is not like BCrypt pattern");
    }
}
