CREATE TABLE usuario (
	codigo BIGINT(20) primary key AUTO_INCREMENT,
    username varchar(100) NOT NULL,
    password varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;