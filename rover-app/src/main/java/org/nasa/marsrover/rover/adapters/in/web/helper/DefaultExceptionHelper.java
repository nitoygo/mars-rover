package org.nasa.marsrover.rover.adapters.in.web.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class DefaultExceptionHelper {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIllegalArgumentException(Exception e) {
        // perform additional handling as needed
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
