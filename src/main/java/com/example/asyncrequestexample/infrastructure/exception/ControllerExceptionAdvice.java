package com.example.asyncrequestexample.infrastructure.exception;

import com.example.asyncrequestexample.vo.Errors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;

import java.util.concurrent.RejectedExecutionException;

@ControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(RejectedExecutionException.class)
    public ResponseEntity<Errors> handlerException(RejectedExecutionException e) {
        return new ResponseEntity<>(new Errors(false, e.getMessage()), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(StringRejectedExecutionException.class)
    public ResponseEntity<String> handlerException(StringRejectedExecutionException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.TOO_MANY_REQUESTS);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Errors> handlerException(Exception e) {
        return new ResponseEntity<>(new Errors(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AsyncRequestTimeoutException.class)
    public ResponseEntity<Errors> handlerException(AsyncRequestTimeoutException e) {return new ResponseEntity<>(new Errors(false, "timeout"), HttpStatus.REQUEST_TIMEOUT);
    }
}
