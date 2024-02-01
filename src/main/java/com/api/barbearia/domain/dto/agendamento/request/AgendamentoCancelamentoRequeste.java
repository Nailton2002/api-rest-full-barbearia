package com.api.barbearia.domain.dto.agendamento.request;

import com.api.barbearia.domain.enums.agendamento.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record AgendamentoCancelamentoRequeste(
        @NotNull Long idAgendamento,
        @NotNull MotivoCancelamento motivo
) {
}
