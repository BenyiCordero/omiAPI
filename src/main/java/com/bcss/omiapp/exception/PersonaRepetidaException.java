package com.bcss.omiapp.exception;

public class PersonaRepetidaException extends RuntimeException {
    public PersonaRepetidaException() {
        super("Persona ya existente");
    }
}
