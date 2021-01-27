package com.rbs.assignment.exception;


import com.rbs.assignment.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Joydeep Paul
 */
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(PrimeNumberProcessingException.class)
    public ResponseEntity<ErrorResponse> primeNumberProcessingException(PrimeNumberProcessingException exc, WebRequest request) {
        return new ResponseEntity<ErrorResponse>(
                new ErrorResponse().code(exc.getCode()).message(exc.getErrorMessage()), HttpStatus.BAD_REQUEST
        );
    }

}
