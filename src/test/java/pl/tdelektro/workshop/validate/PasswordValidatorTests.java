package pl.tdelektro.workshop.validate;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;


@RunWith(MockitoJUnitRunner.class)
public class PasswordValidatorTests {

    @InjectMocks
    private BCryptPasswordEncoder passwordEncoder;
    @InjectMocks
    private PasswordValidator passwordValidator;


    @Test
    public void passwordIsValidNoEncoderTest() {
        String validPassword = "test123";
        assertTrue(passwordValidator.isValid(validPassword,mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void passwordIsValidBCryptTest() {
        String validPassword = passwordEncoder.encode("testPassword");
        assertTrue(passwordValidator.isValid(validPassword,mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void passwordIsValidBCrypt11SaltTest() {
        String inValidPassword = "$2a$11$x9lJru9EkTcj5peV53gKlOSMnZyekJr6WI/kbtRkSAuupysBOwwvS";
        assertFalse(passwordValidator.isValid(inValidPassword,mock(ConstraintValidatorContext.class)));
    }

    @Test
    public void passwordIsValidBCryptLongTest() {
        String inValidPassword = "$2a$10$x9lJru9EkTcj5peV53gKlOSMnZyekJr6WI/kbtRkSAuupysBOwwvS" + "test";
        assertFalse(passwordValidator.isValid(inValidPassword,mock(ConstraintValidatorContext.class)));
    }

}
