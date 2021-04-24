CREATE TABLE pessoa(
	codigo BIGINT(20) primary key AUTO_INCREMENT,
    nome varchar(50) NOT NULL,
    ativo boolean not null,
    logradouro varchar(100) not null,
    numero varchar(10) not null,
    complemento varchar(100),
    bairro varchar(100) not null,
    cep varchar(8) not null,
    cidade varchar(100) not null,
    estado varchar(100) not null
) ENGINE=InnoDB DEFAULT CHARSET=utf8;