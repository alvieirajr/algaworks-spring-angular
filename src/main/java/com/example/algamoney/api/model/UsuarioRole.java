package com.example.algamoney.api.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * UsuariosRole
 * 
 * @author vladimir.stankovic
 *
 *         Aug 18, 2016
 */
@Entity
@Table(name = "usuario_role")
public class UsuarioRole {
    @Embeddable
    public static class Id implements Serializable {
        private static final long serialVersionUID = 1322120000551624359L;
        
        @Column(name = "codigousuario")
        protected Long codigoUsuario;
        
        @Enumerated(EnumType.STRING)
        @Column(name = "ROLE")
        protected Role role;
        
        public Id() { }

        public Id(Long codigoUsuario, Role role) {
            this.codigoUsuario = codigoUsuario;
            this.role = role;
        }
    }
    
    @EmbeddedId
    Id id = new Id();
    
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", insertable=false, updatable=false)
    protected Role role;

    public Role getRole() {
        return role;
    }
}