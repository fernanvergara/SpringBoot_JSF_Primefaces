package com.sti.example.exception;

public class GeneralError extends RuntimeException {
    public GeneralError(String mensaje) {
        super(mensaje);
    }
}