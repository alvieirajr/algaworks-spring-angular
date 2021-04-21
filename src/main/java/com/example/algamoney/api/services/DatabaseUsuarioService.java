package com.example.algamoney.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Usuario;
import com.example.algamoney.api.repository.UsuarioRepository;
import com.example.algamoney.api.security.UsuarioService;

/**
 * Mock implementation.
 * 
 * @author vladimir.stankovic
 *
 * Aug 4, 2016
 */
@Service
public class DatabaseUsuarioService implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public DatabaseUsuarioService(UsuarioRepository userRepository) {
        this.usuarioRepository = userRepository;
    }
    
    public UsuarioRepository getUserRepository() {
        return usuarioRepository;
    }

    @Override
    public Optional<Usuario> getByUsername(String username) {
        return this.usuarioRepository.findByUsername(username);
    }
}