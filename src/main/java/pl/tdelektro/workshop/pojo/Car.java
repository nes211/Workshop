package pl.tdelektro.workshop.pojo;

import jakarta.persistence.*;
import lombok.*;

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


    @Column(name = "vin")
    @NonNull
    String vinNumber;

    @Column(name = "model")
    @NonNull
    String model;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    List<Task> taskList;

}
