package com.sti.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sti.example.model.Rol;
import com.sti.example.model.Usuario;
import com.sti.example.repository.RolRepository;
import com.sti.example.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService{
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, RolRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(String username, String password, String email) {


        Usuario usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(passwordEncoder.encode(password));
        usuario.setEmail(email);
        // Asignar rol por defecto
        if((rolRepository.findByNombre("ROLE_USER")).isEmpty()){
            rolRepository.save(new Rol(null, "ROLE_USER", null));
        }
        Optional<Rol> rolUsuario = rolRepository.findByNombre("ROLE_USER");
//                .orElseThrow(() -> new ObjectNotFoundException("Role 'ROLE_USER' not found"));
        usuario.setRoles(List.of(rolUsuario.get()));
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override  
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }
}
