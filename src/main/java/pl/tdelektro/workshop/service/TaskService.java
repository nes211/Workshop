package pl.tdelektro.workshop.service;

import pl.tdelektro.workshop.pojo.Task;

import java.util.List;

public interface TaskService {

    Task addTask(String toDoTaskName);

    void deleteTask(Long carId, Long taskId);

    void updateTask(String toDoTask);
    List<Task> getAllTasks();
    List<Task> getTasksRegistredToCar(Long carId);
    Task getTaskById (Long taskId);

    List<Task> getUserTasks(Long userId);

    List<Task> getCarTasks(Long carId);

}
