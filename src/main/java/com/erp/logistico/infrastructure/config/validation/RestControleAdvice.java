package com.erp.logistico.infrastructure.config.validation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class RestControleAdvice {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroApiDTO> validation(RuntimeException exception, HttpServletRequest request){
        return buildErrorResponse(exception, HttpStatus.UNAUTHORIZED, request,exception.getMessage());

    }
    private ResponseEntity<ErroApiDTO> buildErrorResponse(Exception ex, HttpStatus status, HttpServletRequest request, String msg) {
        ErroApiDTO errorDTO = new ErroApiDTO(
                OffsetDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                msg,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(errorDTO);
    }
}
