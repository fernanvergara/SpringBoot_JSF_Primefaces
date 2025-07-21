package com.sti.example.exception;

public class ObjetoNoEncontradoError extends RuntimeException {
    public ObjetoNoEncontradoError(String mensaje) {
        super(mensaje);
    }
}