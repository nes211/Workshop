package pl.tdelektro.workshop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "task")
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@JacksonXmlRootElement(localName = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    Long id;

    @NonNull
    @Column(name = "todo_task", unique = true)
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "toDoTaskName")
    String toDoTaskName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = " cars_tasks",
            joinColumns = @JoinColumn(name = "task_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "car_id", referencedColumnName = "id"))
    private List<Car> cars;

    public void setToDoTaskName(String toDoTaskName) {
        this.toDoTaskName = toDoTaskName;
    }

    void setCars(List<Car> cars) {
        this.cars = cars;
    }
}
