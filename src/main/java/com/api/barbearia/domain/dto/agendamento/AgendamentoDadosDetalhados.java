package com.api.barbearia.domain.dto.agendamento;

import com.api.barbearia.domain.entity.agendamento.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoDadosDetalhados(Long id, Long IdBarbeiro, Long idCliente, LocalDateTime data) {

    public AgendamentoDadosDetalhados(Agendamento obj){
        this(obj.getId(), obj.getBarbeiro().getId(), obj.getCliente().getId(), obj.getData());
    }
}
