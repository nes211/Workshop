package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.tdelektro.workshop.exception.PasswordCheckException;
import pl.tdelektro.workshop.validate.PasswordValidator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class VinValidatorTests {


    @InjectMocks
    private VinValidator vinValidator;

    @Test
    public void vinValidCrcTest() {
        String validVin = "1GNEK13ZX3R123456";
        assertEquals(vinValidator.isValid(validVin,mock(ConstraintValidatorContext.class)),true);
    }

    @Test
    public void vinValidLengthTest() {
        String validVin = "1GNEK13ZX3R12345";
        assertEquals(vinValidator.isValid(validVin,mock(ConstraintValidatorContext.class)),false);
    }

    @Test
    public void vinInvalidNotAllowedCharsTest() {
        String validVin = "1GOOZ13ZX3R123456";
        assertEquals(vinValidator.isValid(validVin,mock(ConstraintValidatorContext.class)),false);
    }

}
