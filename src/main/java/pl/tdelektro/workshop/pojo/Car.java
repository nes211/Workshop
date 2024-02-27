package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import pl.tdelektro.workshop.validate.Vin;

import java.util.List;


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

    @ManyToMany
    @JoinTable(name = "cars_tasks",
            joinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"))
    List<Task> tasks;

}
