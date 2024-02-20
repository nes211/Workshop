package pl.tdelektro.workshop.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.service.TaskServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    TaskServiceImpl taskService;

    //List of all tasks from repository
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    //List of tasks assigned to car by carId
    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Task>> getCarTasks(@PathVariable Long carId) {
        return new ResponseEntity<>(taskService.getTasksAssignedToTheCar(carId), HttpStatus.OK);
    }

    //List of tasks assigned to user by userId
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
        return new ResponseEntity<>(taskService.getUserTasks(userId), HttpStatus.OK);
    }

    //List of all to-do tasks assigned to all users
    @GetMapping("/all/todo")
    public ResponseEntity<List<Task>> getAllToDoTasks() {
        return new ResponseEntity<>(taskService.getAllToDoTasks(), HttpStatus.OK);
    }


    //Delete the task from task repository
    @DeleteMapping("/{taskId}")
    public ResponseEntity<HttpStatus> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete the task assigned to the car by carId and taskId
    @DeleteMapping("/{taskId}/car/{carId}")
    public ResponseEntity<HttpStatus> deleteTaskAsignedToCar(@PathVariable Long taskId, @PathVariable Long carId) {
        taskService.deleteTaskAssignedToCar(carId, taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Add a new task
    @PostMapping("/add")
    public ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    //Update task name by taskId and taskToDoName
    @PutMapping("/update/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(taskId, task), HttpStatus.OK);
    }

    //Assign task with car
    @PostMapping("/{taskId}/assign/{carId}")
    public ResponseEntity<HttpStatus> assignTaskWithCar(@PathVariable Long taskId, @PathVariable Long carId) {
        taskService.assignTaskToCar(taskId, carId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
