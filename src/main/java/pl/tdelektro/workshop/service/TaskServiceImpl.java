package pl.tdelektro.workshop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.TaskNotFoundException;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.pojo.Car;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.pojo.User;
import pl.tdelektro.workshop.repository.CarRepository;
import pl.tdelektro.workshop.repository.TaskRepository;
import pl.tdelektro.workshop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    TaskRepository taskRepository;
    UserRepository userRepository;
    private final CarRepository carRepository;

    @Override
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        Task task = unwrapTask(taskId);
        taskRepository.delete(task);
    }

    @Override
    public void deleteTaskAssignedToCar(Long carId, Long taskId) {
        Task task = unwrapTask(taskId);
        taskRepository.delete(task);
    }



    @Override
    public Task updateTask(Long taskId, Task task) {
        Task taskToUpdate = unwrapTask(taskId);
        taskToUpdate.setToDoTaskName(task.getToDoTaskName());
        return taskRepository.save(taskToUpdate);
    }

    @Override
    public List<Task> getAllTasks() {
        return (List<Task>) taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksAssignedToTheCar(Long carId) {
        List<Task> taskList = taskRepository.findByCars_Id(carId);
        return taskList;
    }

    @Override
    public Task getTaskById(Long taskId) {
        Task task = unwrapTask(taskId);
        return task ;
    }

    @Override
    public List<Task> getUserTasks(Long userId) {
        Optional<User> user =  userRepository.findById(userId);
        List<Task> taskList = new ArrayList<>();
        if(user.isPresent()){
            user.get().getCars().forEach(car-> car.getTasks().forEach(task -> taskList.add(task)));
            return taskList;
        }else{
            throw new UserNotFoundException(userId);
        }

    }

    @Override
    public List<Task> getAllToDoTasks() {
        List<Task> listAllToDoTasks = new ArrayList<>();
        List<User> userList = userRepository.findByIdNotNull();
        userList.forEach(user ->
                taskRepository.findByCars_User_Id(user.getId()).forEach(task -> listAllToDoTasks.add(task))
                );
        return listAllToDoTasks;
    }

    @Override
    public void assignTaskToCar(Long taskId, Long carId) {
      Task task = unwrapTask(taskId);
      Car car = unwrapCar(carId);
      car.getTasks().add(task);
      carRepository.save(car) ;
    }

    private Task unwrapTask(Long taskId){
        Optional<Task> task = taskRepository.findById(taskId);
        if(task.isPresent()){
            return task.get();
        }else{
            throw new TaskNotFoundException(taskId);
        }
    }
    private Car unwrapCar(Long carId){
        Optional<Car> car = carRepository.findById(carId);
        if(car.isPresent()){
            return car.get();
        }else{
            throw new CarNotFoundException(carId);
        }
    }

}
