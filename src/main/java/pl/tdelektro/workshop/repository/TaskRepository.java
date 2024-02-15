package pl.tdelektro.workshop.repository;

import org.springframework.data.repository.CrudRepository;
import pl.tdelektro.workshop.pojo.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
