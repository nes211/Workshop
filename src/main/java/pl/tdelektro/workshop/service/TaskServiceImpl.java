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
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long carId, Long taskId) {
        Task task = unwrapTask(taskId);
        taskRepository.delete(task);
    }

    @Override
    public Task updateTask(Long taskId, Task task) {
        Task taskToUpdate = unwrapTask(taskId);
        taskToUpdate.setToDoTaskName(task.getToDoTaskName());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksAssignedToTheCar(Long carId) {
        List<Task> taskList = taskRepository.findByCar_Id(carId);

        return taskList;
    }

    @Override
    public Task getTaskById(Long taskId) {
        Task task = unwrapTask(taskId);
        return task ;
    }

    @Override
    public List<Task> getUserTasks(Long userId) {
        return taskRepository.findByUser_Id(userId);
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
