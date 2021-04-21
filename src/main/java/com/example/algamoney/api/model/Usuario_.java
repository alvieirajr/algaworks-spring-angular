package com.example.algamoney.api.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Usuario.class)
public abstract class Usuario_ {

	public static volatile SingularAttribute<Usuario, Long> codigo;
	public static volatile SingularAttribute<Usuario, String> password;
	public static volatile ListAttribute<Usuario, UsuarioRole> roles;
	public static volatile SingularAttribute<Usuario, String> username;

	public static final String CODIGO = "codigo";
	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String USERNAME = "username";

}

