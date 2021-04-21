CREATE TABLE usuario_role(
	codigoUsuario BIGINT(20) AUTO_INCREMENT,
    role varchar(50) NOT NULL,
    primary key (codigoUsuario, role),
	foreign key (codigoUsuario) references usuario(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;