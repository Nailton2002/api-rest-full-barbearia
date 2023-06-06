create table agendamentos(

    id bigint not null auto_increment,
    barbeiro_id bigint not null,
    cliente_id bigint not null,
    data datetime not null,
    motivo_cancelamento varchar(255),

    primary key(id),
    constraint fk_agendamentos_barbeiro_id foreign key(barbeiro_id) references barbeiros(id),
    constraint fk_agendamentos_cliente_id foreign key(cliente_id) references clientes(id)

);