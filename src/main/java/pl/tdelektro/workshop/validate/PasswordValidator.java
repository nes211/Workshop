package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.tdelektro.workshop.exception.PasswordCheckException;


public class PasswordValidator implements ConstraintValidator<Password, String> {


    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        //Check for BCrypt password string
        if (s.contains("$2a$10$") && (s.length() == (60) || s.length() == (65))) {
            return true;
        }else{
            throw new PasswordCheckException();
        }
    }
}
