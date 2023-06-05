package com.api.barbearia.controller.agendamento.dto;

import com.api.barbearia.domain.agendamento.entity.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoDadosDetalhados(Long id, Long IdBarbeiro, Long idCliente, LocalDateTime data) {

    public AgendamentoDadosDetalhados(Agendamento obj){
        this(obj.getId(), obj.getBarbeiro().getId(), obj.getCliente().getId(), obj.getData());
    }
}
