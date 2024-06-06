package com.example.demo.exception.global;


import com.example.demo.dto.ProjectResponse;
import com.example.demo.exception.local.SignUpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<ProjectResponse> handleEmailExistException(SignUpException e){
        ProjectResponse projectResponse=new ProjectResponse("01",e.getMessage(),null);
        return new ResponseEntity<>(projectResponse, HttpStatus.CONFLICT);
    }

}
