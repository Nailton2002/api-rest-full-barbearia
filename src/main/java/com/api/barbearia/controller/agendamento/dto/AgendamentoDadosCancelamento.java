package com.api.barbearia.controller.agendamento.dto;

import com.api.barbearia.domain.agendamento.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record AgendamentoDadosCancelamento(
        @NotNull Long idConsulta,
        @NotNull MotivoCancelamento motivo
) {
}
