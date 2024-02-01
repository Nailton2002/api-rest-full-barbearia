package com.api.barbearia.domain.dto.agendamento.request;

import com.api.barbearia.domain.enums.barbeiro.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        Long idBarbeiro,
        @NotNull
        Long idCliente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
)
{ }
