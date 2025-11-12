package com.bcss.omiapp.exception;

public class CredencialesInvalidasException extends RuntimeException {
    public CredencialesInvalidasException() {
        super("Credenciales invalidas");
    }
}
