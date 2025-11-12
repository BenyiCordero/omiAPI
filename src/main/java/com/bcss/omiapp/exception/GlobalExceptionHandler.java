package com.bcss.omiapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PersonaRepetidaException.class)
    public ResponseEntity<String> handleException(PersonaRepetidaException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> handleException(UsuarioNoEncontradoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(CredencialesInvalidasException.class)
    public ResponseEntity<String> handleException(CredencialesInvalidasException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @ExceptionHandler(TokenInvalidoException.class)
    public ResponseEntity<String> handleException(TokenInvalidoException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
