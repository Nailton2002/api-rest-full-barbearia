package com.api.barbearia.application.agendamento.dto;

import com.api.barbearia.domain.agendamento.enums.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record AgendamentoDadosCancelamento(
        @NotNull Long idAgendamento,
        @NotNull MotivoCancelamento motivo
) {
}
