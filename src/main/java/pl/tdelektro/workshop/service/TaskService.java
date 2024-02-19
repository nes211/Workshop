package pl.tdelektro.workshop.service;

import pl.tdelektro.workshop.pojo.Task;

import java.util.List;

public interface TaskService {

    Task addTask(Task task);

    void deleteTask(Long carId, Long taskId);

    Task updateTask(Long taskId, Task task);
    List<Task> getAllTasks();
    List<Task> getTasksRegistredToCar(Long carId);
    Task getTaskById (Long taskId);

    List<Task> getUserTasks(Long userId);


}
