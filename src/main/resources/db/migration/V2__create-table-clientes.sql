create table clientes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone varchar(100) not null unique,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    numero varchar(20),
    complemento varchar(100),
    cidade varchar(100) not null,
    uf char(2) not null,
    ativo tinyint not null,

    primary key(id)

);