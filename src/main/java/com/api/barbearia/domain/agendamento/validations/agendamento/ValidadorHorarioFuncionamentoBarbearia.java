package com.api.barbearia.domain.agendamento.validations.agendamento;

import com.api.barbearia.controller.agendamento.dto.AgendamentoDadosCadastro;
import com.api.barbearia.domain.agendamento.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoBarbearia implements ValidadorAgendamento{

    public void validar(AgendamentoDadosCadastro dados){

        var dataAgendamento = dados.data();
        var domingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaBarbearia = dataAgendamento.getHour() < 7;
        var depoisDoEncerramentoDaBarbearia = dataAgendamento.getHour() > 18;

        if (domingo || antesDaAberturaDaBarbearia || depoisDoEncerramentoDaBarbearia) {
            throw new ValidacaoException("Agendamento fora do horário de funcionamento da barbearia!");
        }
    }
}
