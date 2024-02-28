package pl.tdelektro.workshop.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Profile("test")
@PropertySource("classpath:admin-test.properties")
public class Admin {
    //@Value("${email.password}")
    private String password;



}
