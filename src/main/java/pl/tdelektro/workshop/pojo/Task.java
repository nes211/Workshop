package pl.tdelektro.workshop.pojo;

import jakarta.persistence.*;
import lombok.*;

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
    Long id;

    @NonNull
    @Column(name = "todo_task")
    String toDoTask;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;


}
