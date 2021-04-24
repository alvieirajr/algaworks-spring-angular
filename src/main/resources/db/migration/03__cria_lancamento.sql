create table lancamento (
	codigo bigint(20) primary key auto_increment,
    descricao varchar(50) not null,
    data_vencimento date not null,
    data_pagamento date,
    valor decimal(10,2) not null,
    observacao varchar(100),
    tipo varchar(20) not null,
    codigo_categoria bigint(20) not null,
    codigo_pessoa bigint(20) not null,
    foreign key (codigo_categoria) references categoria(codigo),
    foreign key (codigo_pessoa) references pessoa(codigo)
) ENGINE=InnoDb default charset=utf8;