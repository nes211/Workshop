package pl.tdelektro.workshop.validate;

import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@AllArgsConstructor
@RunWith(MockitoJUnitRunner.class)
public class PasswordValidatorTests {

    private PasswordValidator passwordValidator;
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void passwordValidatorNoEncoderTest (){

    }

    @Test
    public void passwordValidatorBCryptShortTest (){
    }

    @Test
    public void passwordValidatorBCrypt11SaltTest (){
    }
      @Test
    public void passwordValidatorBCryptLongTest (){
    }


}
