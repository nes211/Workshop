package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.tdelektro.workshop.exception.PasswordCheckException;


public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {

        if (s.contains("$2a$10$") && s.length() == 60) {
            return true;
        } else if (s.matches(".*[a-z].") ||
                        s.matches(".*\\d.") &&
                        !s.matches(".*[!@#$%^&*()_;',./<>?:].*")) {
            return true;
        }
        throw new PasswordCheckException();
    }
}
