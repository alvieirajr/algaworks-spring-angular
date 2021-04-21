package com.example.algamoney.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id 
    @Column(name="codigo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    
    @Column(name="username")
    private String username;
    
    @Column(name="password")
    private String password;
    
    @OneToMany
    @JoinColumn(name="codigousuario", referencedColumnName="codigo")
    private List<UsuarioRole> roles;
    
    public Usuario() { }
    
    public Usuario(Long codigo, String username, String password, List<UsuarioRole> roles) {
        this.codigo = codigo;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<UsuarioRole> getRoles() {
        return roles;
    }
}