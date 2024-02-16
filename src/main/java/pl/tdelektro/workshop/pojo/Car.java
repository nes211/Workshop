package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    User user;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL)
    List<Task> taskList;

}
