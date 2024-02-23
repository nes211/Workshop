package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import pl.tdelektro.workshop.validate.Password;


import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
public class User{
    public User(@NonNull String email, @NonNull String password) {
        this.email = email;
        this.password = password;
        this.roles = Arrays.asList("ROLE_USER");
        this.username = email;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "userEmail", unique = true)
    @Email(message =" Incorrect email address")
    @NonNull
    String email;



    @Column(name = "username", unique = true)
    String username;

    @Column(name = "authorities")
    List<String> roles;


    @Column(name="password")
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


}
