package com.sti.example.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.sti.example.enums.RolUsuario;
import com.sti.example.model.Rol;
import com.sti.example.model.Usuario;
import com.sti.example.repository.RolRepository;
import com.sti.example.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Component
public class UserInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitializer(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario adminUser = new Usuario();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("1234567890"));
            adminUser.setEmail("fernanvergara@gmail.com"); // Cambiar email por si es necesario

            Optional<Rol> devRolOptional = rolRepository.findByNombre(RolUsuario.DEV.name());
            Rol devRol;
            if (devRolOptional.isPresent()) {
                devRol = devRolOptional.get();
            } else {
                devRol = new Rol();
                devRol.setNombre(RolUsuario.DEV.name());
                rolRepository.save(devRol);
            }

            adminUser.setRoles(List.of(devRol));
            usuarioRepository.save(adminUser);
            System.out.println("Usuario 'admin' creado con rol "+RolUsuario.DEV.name());
        } else {
            System.out.println("El usuario 'admin' ya existe en la base de datos.");
        }
    }
}