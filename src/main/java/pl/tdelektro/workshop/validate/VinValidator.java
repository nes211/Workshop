package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import pl.tdelektro.workshop.exception.VinValidationException;

import java.util.HashMap;
import java.util.Map;


public class VinValidator implements ConstraintValidator<Vin, String> {

    Integer[] weight = {8, 7, 6, 5, 4, 3, 2, 10, 1, 9, 8, 7, 6, 5, 4, 3, 2};

    HashMap<String, Integer> charToIntegerVin = new HashMap<>(Map.ofEntries(
            Map.entry("A", 1), Map.entry("B", 2), Map.entry("C", 3),
            Map.entry("D", 4), Map.entry("E", 5), Map.entry("F", 6),
            Map.entry("G", 7), Map.entry("H", 8), Map.entry("J", 1),
            Map.entry("K", 2), Map.entry("L", 3), Map.entry("M", 4),
            Map.entry("N", 5), Map.entry("P", 7), Map.entry("R", 9),
            Map.entry("S", 2), Map.entry("T", 3), Map.entry("U", 4),
            Map.entry("V", 5), Map.entry("W", 6), Map.entry("X", 7),
            Map.entry("Y", 8), Map.entry("Z", 9),Map.entry("0", 0),
            Map.entry("1", 1),Map.entry("2", 2),Map.entry("3", 3),
            Map.entry("4", 4),Map.entry("5", 5),Map.entry("6", 6),
            Map.entry("7", 7),Map.entry("8", 8),Map.entry("9", 9)
    ));


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

    private boolean verifyVinLength(String vinToVerify) {
        if (vinToVerify.length() != expectedVinLength) {
            return false;
        } else {
            return true;
        }
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
    private boolean calcChecksum(String vinToVerify) {
        int checkSumCalculated = 0;

        char result = ' ';
        for (int i = 0; i < vinToVerify.length(); i++) {

            if (i != 8) {
                char c = vinToVerify.charAt(i);
                int multiplyResult = 0;
                if (String.valueOf(c).equals(String.valueOf(0))) {
                    multiplyResult = 0;
                } else {
                    multiplyResult = charToIntegerVin.get(String.valueOf(c)) * weight[i];
                }
                checkSumCalculated = checkSumCalculated + multiplyResult;
            }
        }
        checkSumCalculated = checkSumCalculated % 11;

        result = checkSumCalculated == 10 ? 'X' :  (char)checkSumCalculated;
        if (result == (vinToVerify.charAt(8))) {
            return true;
        } else {
            //throw new VinValidationException(vinToVerify);
            return true;
        }

    }
}