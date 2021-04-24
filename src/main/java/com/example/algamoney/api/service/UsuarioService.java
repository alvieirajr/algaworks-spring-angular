package com.example.algamoney.api.service;

import java.util.Optional;

import com.example.algamoney.api.model.Usuario;


/**
 * 
 * @author vladimir.stankovic
 *
 * Aug 17, 2016
 */
public interface UsuarioService {
    public Optional<Usuario> getByUsername(String username);
}