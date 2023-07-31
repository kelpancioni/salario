package com.example.salario.infra;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threat404() {
        return ResponseEntity.badRequest().body("Dado não encontrado.");
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity threat403() {
        return ResponseEntity.badRequest().body("Funcionário já cadastrado.");
    }
}
