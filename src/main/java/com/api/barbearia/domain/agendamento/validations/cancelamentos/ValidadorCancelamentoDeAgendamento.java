package com.api.barbearia.domain.agendamento.validations.cancelamentos;

import com.api.barbearia.controller.agendamento.dto.AgendamentoDadosCancelamento;

public interface ValidadorCancelamentoDeAgendamento {
    void validar(AgendamentoDadosCancelamento dados);
}
