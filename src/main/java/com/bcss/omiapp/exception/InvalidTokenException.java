package com.bcss.omiapp.exception;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Token invalido");
    }
}
