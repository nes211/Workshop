package pl.tdelektro.workshop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.pojo.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByToDoTaskName(String toDoTaskName);

    List<Task> findByCars_Id(Long id);

    List<Task> findByCars_User(User user);

    List<Task> findByCars_User_Id(Long id);
}
