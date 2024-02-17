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


    //List of all tasks
    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    //List of tasks assigned to user cars by userID
    @GetMapping("/{userId}")
    public ResponseEntity<List<Task>> getUserTasks(@PathVariable Long userId) {
        return new ResponseEntity<>(taskService.getUserTasks(userId), HttpStatus.OK);
    }

    //List of tasks assigned to car by carId
    @GetMapping("/car/{carId}")
    public ResponseEntity<List<Task>> getCarTasks(@PathVariable Long carId) {
        return new ResponseEntity<>(taskService.getTasksRegistredToCar(carId), HttpStatus.OK);
    }

    @DeleteMapping("/car/{carId}/{taskId}")
    public ResponseEntity<HttpStatus> deleteCarTask(@PathVariable Long carId, @PathVariable Long taskId) {
        taskService.deleteTask(carId, taskId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/new")
    public ResponseEntity<Task>addNewTask(@RequestBody String taskToDo){

        ;


        return new ResponseEntity<>(taskService.addTask(taskToDo), HttpStatus.CREATED);
    }

}
