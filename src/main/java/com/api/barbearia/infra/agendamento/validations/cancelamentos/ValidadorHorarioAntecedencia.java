package com.api.barbearia.infra.agendamento.validations.cancelamentos;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoCancelamentoRequeste;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaCancelamento")
public class ValidadorHorarioAntecedencia implements ValidadorCancelamentoDeAgendamento{

    @Autowired
    private AgendamentoRepository repository;
    @Override
    public void validar(AgendamentoCancelamentoRequeste request) {
        var agendamento = repository.getReferenceById(request.idAgendamento());
        var agora = LocalDateTime.now();
        var diferencaEmHoras = Duration.between(agora, agendamento.getData()).toHours();

        if (diferencaEmHoras < 24) {
            throw new ValidacaoException("Agendamento só pode ser cancelada com antecedência mínima de 24h!");
        }
    }
}
