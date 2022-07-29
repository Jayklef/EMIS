package com.jerrycodes.emis.exception;

import com.jerrycodes.emis.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

@ResponseStatus
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> departmentNotFoundException(DepartmentNotFoundException exception,
                                                                    ServletWebRequest request){

        ErrorMessage message = new ErrorMessage();
        message.setTimestamp(Timestamp.from(Instant.now()));
        message.setStatus(HttpStatus.NOT_FOUND.toString());
        message.setError(message.getError());
        message.setMessage(message.getMessage());

        exception.getMessage();
        exception.getCause();
        request.getRequest().getRequestURI();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ErrorMessage> employeeNotFoundException(EmployeeNotFoundException exception,
                                                                  ServletWebRequest request){
        ErrorMessage message = new ErrorMessage();
        message.setTimestamp(Timestamp.from(Instant.now()));
        message.setStatus(HttpStatus.NOT_FOUND.toString());
        message.setError(message.getError());
        message.setMessage(message.getMessage());

        exception.getCause();
        request.getRequest().getRequestURI();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ErrorMessage> clientNotFoundException(ClientNotFoundException exception,
                                                                ServletWebRequest request){
        ErrorMessage message = new ErrorMessage();
        message.setTimestamp(Timestamp.from(Instant.now()));
        message.setStatus(HttpStatus.NOT_FOUND.toString());
        message.setError(message.getError());
        message.setMessage(message.getMessage());

        exception.getCause();
        request.getRequest().getRequestURI();

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }
}
