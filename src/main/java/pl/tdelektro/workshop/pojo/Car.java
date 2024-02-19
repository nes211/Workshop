package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.tdelektro.workshop.repository.TaskRepository;
import pl.tdelektro.workshop.validate.Vin;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "vin", unique = true)
    @NonNull
    @Vin
    String vinNumber;

    @Column(name = "model")
    @NonNull
    String model;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    List<Task> taskList;

}
