package com.api.barbearia.controller.agendamento.dto;

import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AgendamentoDadosCadastro(
        Long idBarbeiro,
        @NotNull
        Long idCliente,
        @NotNull
        @Future
        LocalDateTime data,
        Especialidade especialidade
)
{ }
