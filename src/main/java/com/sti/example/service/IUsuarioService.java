package com.sti.example.service;

import java.util.Optional;

import com.sti.example.model.Usuario;

public interface IUsuarioService {
    Optional<Usuario> findByUsername(String username);
    
}
