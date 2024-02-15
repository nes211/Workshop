package pl.tdelektro.workshop.pojo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "car")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    @Column(name = "vin")
    @NonNull
    String vinNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
}
