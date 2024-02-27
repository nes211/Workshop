package pl.tdelektro.workshop.security;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Admin {
    @Value("${admin.password}")
    private String adminPassword;

    public String getPassword() {
        return adminPassword;
    }
}
