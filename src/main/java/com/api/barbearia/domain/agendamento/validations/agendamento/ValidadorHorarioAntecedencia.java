package com.api.barbearia.domain.agendamento.validations.agendamento;

import com.api.barbearia.application.agendamento.dto.AgendamentoDadosCadastro;
import com.api.barbearia.domain.agendamento.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorHorarioAntecedenciaAgendamento")
public class ValidadorHorarioAntecedencia implements ValidadorAgendamento{

    public void validar(AgendamentoDadosCadastro dados) {

        var dataAgendamento = dados.data();
        var agora = LocalDateTime.now();
        var diferencaEmMinutos = Duration.between(agora, dataAgendamento).toMinutes();

        if (diferencaEmMinutos < 30) {
            throw new ValidacaoException("Agendamento deve ser com antecedência mínima de 30 minutos");
        }

    }
}
