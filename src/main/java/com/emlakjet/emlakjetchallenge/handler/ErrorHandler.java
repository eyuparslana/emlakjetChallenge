package com.emlakjet.emlakjetchallenge.handler;

import com.emlakjet.emlakjetchallenge.exception.EstateAgencyAlreadyExistsException;
import com.emlakjet.emlakjetchallenge.exception.EstateAgencyNotFoundException;
import com.emlakjet.emlakjetchallenge.exception.EstateNotFoundException;
import com.emlakjet.emlakjetchallenge.model.ApiResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ErrorHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseBody> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request, HttpServletResponse response) {

        List<String> errors = new ArrayList<String>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errors.add(error.getDefaultMessage());
        }

        log.error("Validation Error!", ex);
        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .description("Validation Error")
                .errors(errors)
                .build();
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EstateAgencyNotFoundException.class)
    public ResponseEntity<ApiResponseBody> handleEstateAgencyNotFoundException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .description(ex.getMessage())
                .errors(ex.getMessage())
                .build();
        log.error("Agency not found", ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstateNotFoundException.class)
    public ResponseEntity<ApiResponseBody> handleEstateNotFoundException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .description(ex.getMessage())
                .errors(ex.getMessage())
                .build();
        log.error("Estate not found", ex);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstateAgencyAlreadyExistsException.class)
    public ResponseEntity<ApiResponseBody> handleEstateAgencyAlreadyExistsException(Exception ex, HttpServletRequest request, HttpServletResponse response) {

        ApiResponseBody body = ApiResponseBody.builder()
                .status(HttpStatus.CONFLICT.value())
                .description(ex.getMessage())
                .errors(ex.getMessage())
                .build();

        log.error("Agency already exists.", ex);
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

}
