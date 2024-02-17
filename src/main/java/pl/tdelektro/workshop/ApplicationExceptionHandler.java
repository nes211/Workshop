package pl.tdelektro.workshop;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.tdelektro.workshop.exception.CarNotFoundException;
import pl.tdelektro.workshop.exception.ErrorResponse;
import pl.tdelektro.workshop.exception.UserNotFoundException;
import pl.tdelektro.workshop.exception.VinValidationException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class,
            CarNotFoundException.class,
            VinValidationException.class
    })
    public ResponseEntity<Object> handleResource(RuntimeException ex) {
        ErrorResponse errors = new ErrorResponse(Arrays.asList(ex.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
