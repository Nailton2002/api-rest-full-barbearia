package com.api.barbearia.domain.dto.agendamento;

import com.api.barbearia.domain.enums.agendamento.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record AgendamentoDadosCancelamento(
        @NotNull Long idAgendamento,
        @NotNull MotivoCancelamento motivo
) {
}
