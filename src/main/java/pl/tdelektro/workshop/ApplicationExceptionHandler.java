package pl.tdelektro.workshop;

import jakarta.mail.MessagingException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.tdelektro.workshop.exception.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {


    //The exceptions related to application layer exceptions
    @ExceptionHandler({UserNotFoundException.class,
            CarNotFoundException.class,
            TaskNotFoundException.class,
            VinValidationException.class,
            CarAlreadyRegisteredException.class,
            UserNotAssignedWithEmailException.class

    })
    public ResponseEntity<Object> handleResource(RuntimeException ex) {
        ErrorResponse errors = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    //An exception related to data defined in custom constraint for password
    @ExceptionHandler(PasswordCheckException.class)
    public ResponseEntity<Object> handlePasswordException(ValidationException ex) {
        ErrorResponse errors = new ErrorResponse(Arrays.asList(ex.getCause().getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }



    //An exception related to data defined in the database as unique
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
        ErrorResponse error = new ErrorResponse(Arrays.asList("Element already exist in repository"));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<Object> handleEmailServiceException(MessagingException ex){
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(error,HttpStatus.SERVICE_UNAVAILABLE);
    }


    //Handle the application argument exceptions with custom constraints for VIN
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request)
    {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
