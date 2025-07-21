package com.sti.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sti.example.model.Rol;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByNombre(String nombre);
}