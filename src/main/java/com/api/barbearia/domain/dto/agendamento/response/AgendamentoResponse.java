package com.api.barbearia.domain.dto.agendamento.response;

import com.api.barbearia.domain.entity.agendamento.Agendamento;

import java.time.LocalDateTime;

public record AgendamentoResponse(Long id, Long IdBarbeiro, Long idCliente, LocalDateTime data) {

    public AgendamentoResponse(Agendamento obj){
        this(obj.getId(), obj.getBarbeiro().getId(), obj.getCliente().getId(), obj.getData());
    }
}
