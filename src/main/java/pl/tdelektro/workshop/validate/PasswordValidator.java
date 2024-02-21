package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.matches(".*[a-z].") ||
                s.matches(".*\\d.")) {
            return true;
        } else {
            throw new RuntimeException("Password invalid. a-z characters and number");
        }

    }
}
