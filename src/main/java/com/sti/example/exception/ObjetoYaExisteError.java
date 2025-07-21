package com.sti.example.exception;

public class ObjetoYaExisteError extends RuntimeException {
    public ObjetoYaExisteError(String mensaje) {
        super(mensaje);
    }
}