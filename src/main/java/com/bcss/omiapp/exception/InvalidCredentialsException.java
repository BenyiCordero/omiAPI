package com.bcss.omiapp.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Credenciales invalidas");
    }
}
