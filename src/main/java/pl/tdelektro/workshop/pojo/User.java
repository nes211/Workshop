package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "userEmail")
    @Email(message =" Incorrect email address")
    @NonNull
    String email;


    @Column(name="password")
    @NotEmpty(message = "Password can not be blank")
    @JsonIgnore
    @NonNull
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
