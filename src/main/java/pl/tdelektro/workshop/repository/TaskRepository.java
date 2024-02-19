package pl.tdelektro.workshop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.tdelektro.workshop.pojo.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByToDoTaskName(String toDoTaskName);

    List<Task> findByCars_Id(Long id);
}
