package com.microservices.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.dto.CustomErrorResponse;
import com.microservices.exception.SwiggyServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class SwiggyServiceGlobalExceptionHandler {

    @ExceptionHandler(SwiggyServiceException.class)
    public ResponseEntity<?> handleSwiggyServiceException(SwiggyServiceException ex) throws JsonProcessingException {
        log.error("SwiggyServiceGlobalExceptionHandler::handleSwiggyServiceException exception caught {}", ex.getMessage());

        CustomErrorResponse errorResponse= CustomErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
               .errorCode("500")
                .ts(LocalDateTime.now())
               .errorMessage(ex.getMessage()).build()  ;

        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
