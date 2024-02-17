package pl.tdelektro.workshop.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.tdelektro.workshop.pojo.Task;
import pl.tdelektro.workshop.service.TaskServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {

    TaskServiceImpl taskService;

    @GetMapping ("/all")
    public ResponseEntity<List<Task>>getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }


}
