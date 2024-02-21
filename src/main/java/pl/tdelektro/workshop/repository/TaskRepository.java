package pl.tdelektro.workshop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.pojo.User;

import java.util.List;

public interface TaskRepository extends CrudRepository<Task, Long> {

    List<Task> findByCars_Id(Long id);

    List<Task> findByCars_User_Id(Long id);
}
