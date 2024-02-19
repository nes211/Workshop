package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    Long id;

    @NonNull
    @Column(name = "todo_task", unique = true)
    String toDoTaskName;

//    @ManyToOne
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
//    User user;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = " cars_tasks",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private List<Car> cars;
}
