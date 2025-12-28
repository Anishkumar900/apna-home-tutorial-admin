package com.admin.exception;

import com.admin.response.ExceptionApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ExceptionApiResponse> handleUserAlreadyExist(UserAlreadyExistException ex){
        ExceptionApiResponse result=new ExceptionApiResponse(
                ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                "Already user exist!",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(result,HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionApiResponse> handlerUserNotFoundException(UserNotFoundException ex){
        ExceptionApiResponse result=new ExceptionApiResponse(
          ex.getMessage(),
          HttpStatus.NOT_FOUND.value(),
          "User not found!",
          LocalDateTime.now()
        );
        return new ResponseEntity<>(result,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AdminSaveException.class)
    public ResponseEntity<ExceptionApiResponse> handlerAdminSaveException(AdminSaveException ex){
        ExceptionApiResponse result=new ExceptionApiResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                "Data didn't save in DB!",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionApiResponse> handlerIllegalArgumentException(IllegalArgumentException ex){
        ExceptionApiResponse result=new ExceptionApiResponse(
                ex.getMessage(),
                HttpStatus.NO_CONTENT.value(),
                "Email cannot be null",
                LocalDateTime.now()
        );
        return new ResponseEntity<>(result,HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionApiResponse> handlerBadCredentialsException(BadCredentialsException ex){
        ExceptionApiResponse result = new ExceptionApiResponse(
          ex.getMessage(),
          HttpStatus.BAD_REQUEST.value(),
          "Invited input",
          LocalDateTime.now()

        );
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }

}
