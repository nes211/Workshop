package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.tdelektro.workshop.validate.Password;

import java.util.List;


@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter

public class User {

    //User authority constructor
    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.username = email;
        this.password = password;
        this.authority = "USER";
        this.roles = "USER";
    }

    //Admin authority constructor
    public User(@NonNull String email, @NonNull String password, String authority) {
        this.email = email;
        this.password = password;
        this.authority = "ADMIN";
        this.username = email;
        this.roles = "ADMIN";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;


    @Column(name = "userEmail", unique = true)
    @Email(message = " Incorrect email address")
    @NonNull
    String email;


    @Column(name = "username", unique = true)
    String username;

    @Column(name = "authorities")
    String authority;

    @Column(name = "role")
    @JsonIgnore
    String roles;

    @Column(name = "password")
    @NotEmpty(message = "Password can not be blank")
    @NonNull
    @Password
    String password;

    @Column(name = "cars")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Car> cars;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public void setEmail(String email) {
        this.email = email;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public User setAuthority(String authority) {
        this.authority = authority;
        return this;
    }

    public User setRoles(String roles) {
        this.roles = roles;
        return this;
    }

    public User setCars(List<Car> cars) {
        this.cars = cars;
        return this;
    }
}
