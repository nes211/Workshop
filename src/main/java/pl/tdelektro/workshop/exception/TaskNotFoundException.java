package pl.tdelektro.workshop.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long taskId){
        super("Task with id : " + taskId + " is not available in repository");
    }
}
