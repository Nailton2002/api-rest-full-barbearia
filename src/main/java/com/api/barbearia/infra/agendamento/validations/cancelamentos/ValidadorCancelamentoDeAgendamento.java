package com.api.barbearia.infra.agendamento.validations.cancelamentos;

import com.api.barbearia.domain.dto.agendamento.AgendamentoDadosCancelamento;

public interface ValidadorCancelamentoDeAgendamento {
    void validar(AgendamentoDadosCancelamento dados);
}
