package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.HashMap;


public class VinValidator implements ConstraintValidator<Vin, String> {

    HashMap<String, Integer> vinMap = new HashMap<>();
    private int expectedVinLength = 17;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return isVinCorrect(s.toUpperCase());
    }

    private boolean isVinCorrect(String vinToVerify) {

        if (verifyVinLength(vinToVerify) && scanNotAllowedChars(vinToVerify) && calcChecksum(vinToVerify)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean calcChecksum(String vinToVerify) {

        return true;
    }

    private boolean scanNotAllowedChars(String vinToVerify) {
        char[] charsFromVin = vinToVerify.toCharArray();

        for (char c : charsFromVin) {
            if (c == 'O' || c == 'I' || c == 'Q') {
                return false;
            }
        }
        return true;
    }

    private boolean verifyVinLength(String vinToVerify) {
        if (vinToVerify.length() != expectedVinLength) {
            return false;
        } else {
            return true;
        }
    }
}
