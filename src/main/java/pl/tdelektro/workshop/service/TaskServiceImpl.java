package pl.tdelektro.workshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.exception.TaskNotFoundException;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    TaskRepository taskRepository;
    @Override
    public Task addTask(String toDoTaskName) {
        Task task = new Task(toDoTaskName);
        return taskRepository.save(task);

    }

    @Override
    public void deleteTask(Long carId, Long taskId) {
        Task task = unwrapTask(taskId);
        taskRepository.delete(task);
    }

    @Override
    public void updateTask(String toDoTask) {



    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksRegistredToCar(Long carId) {
        return taskRepository.findByCar_Id(carId);
    }

    @Override
    public Task getTaskById(Long taskId) {
        return null;
    }

    @Override
    public List<Task> getUserTasks(Long userId) {
        return null;
    }

    @Override
    public List<Task> getCarTasks(Long carId) {
        return null;
    }

    private Task unwrapTask(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            return task.get();
        }else{
            throw new TaskNotFoundException(taskId);
        }
    }

}
