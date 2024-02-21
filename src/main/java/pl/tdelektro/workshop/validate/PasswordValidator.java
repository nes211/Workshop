package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.tdelektro.workshop.exception.PasswordCheckException;


public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.matches(".*[a-z].") ||
                s.matches(".*\\d.") &&
                !s.matches(".*[!@#$%^&*()_;',./<>?:].*"))  {
            return true;
        } else {
            throw new PasswordCheckException();
        }

    }
}
