package pl.tdelektro.workshop.web;

import jakarta.mail.MessagingException;
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

    //
    //All mappings only for ADMIN
    //

    //List of all tasks from repository
    @GetMapping("/all")
    ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    //List of tasks assigned to car by carId
    @GetMapping("/car/{carId}")
    ResponseEntity<List<Task>> getCarTasks(@PathVariable Long carId) {
        return new ResponseEntity<>(taskService.getTasksAssignedToTheCar(carId), HttpStatus.OK);
    }

    //List of tasks assigned to user by userId
    @GetMapping("/user/{userId}")
    ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
        return new ResponseEntity<>(taskService.getUserTasks(userId), HttpStatus.OK);
    }

    //List of all to-do tasks assigned to all users
    @GetMapping("/all/todo")
    ResponseEntity<List<Task>> getAllToDoTasks() {
        return new ResponseEntity<>(taskService.getAllToDoTasks(), HttpStatus.OK);
    }


    //Delete the task from task repository
    @DeleteMapping("/{taskId}")
    ResponseEntity<HttpStatus> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete the task assigned to the car by carId and taskId
    @DeleteMapping("/{taskId}/car/{carId}")
    ResponseEntity<HttpStatus> deleteTaskAsignedToCar(@PathVariable Long taskId, @PathVariable Long carId) throws MessagingException {
        taskService.deleteTaskAssignedToCar(carId, taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Add a new task
    @PostMapping("/add")
    ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.addTask(task), HttpStatus.CREATED);
    }

    //Update task name by taskId and taskToDoName
    @PutMapping("/update/{taskId}")
    ResponseEntity<Task> updateTask(@PathVariable Long taskId, @RequestBody Task task) {
        return new ResponseEntity<>(taskService.updateTask(taskId, task), HttpStatus.OK);
    }

    //Assign task with car
    @PostMapping("/{taskId}/assign/{carId}")
    ResponseEntity<HttpStatus> assignTaskWithCar(@PathVariable Long taskId, @PathVariable Long carId) {
        taskService.assignTaskToCar(taskId, carId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
