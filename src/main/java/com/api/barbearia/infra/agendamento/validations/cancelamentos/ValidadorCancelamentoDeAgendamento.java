package com.api.barbearia.infra.agendamento.validations.cancelamentos;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoCancelamentoRequeste;

public interface ValidadorCancelamentoDeAgendamento {
    void validar(AgendamentoCancelamentoRequeste request);
}
