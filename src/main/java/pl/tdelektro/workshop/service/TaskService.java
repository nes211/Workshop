package pl.tdelektro.workshop.service;

import pl.tdelektro.workshop.pojo.Task;

import java.util.List;

public interface TaskService {

    void addTask(String toDoTaskName);
    void deleteTask(String toDoTaskName);
    void updateTask(String toDoTask);
    List<Task> getAllTasks();
    List<Task> getTasksForCar(String vinNumber);
    Task getTaskById (Long taskId);

}
