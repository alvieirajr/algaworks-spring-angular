CREATE TABLE categoria(
	codigo BIGINT(20) primary key AUTO_INCREMENT,
    nome varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO Categoria (nome) values ('Lazer');
INSERT INTO Categoria (nome) values ('Alimentação');
INSERT INTO Categoria (nome) values ('Supermercado');
INSERT INTO Categoria (nome) values ('Farmácia');
INSERT INTO Categoria (nome) values ('Outros');