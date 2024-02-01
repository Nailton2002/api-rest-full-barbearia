package com.api.barbearia.infra.agendamento.validations.agendamento;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidadorHorarioFuncionamentoBarbearia implements ValidadorAgendamento{

    public void validar(AgendamentoRequest request){

        var dataAgendamento = request.data();
        var domingo = dataAgendamento.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDaAberturaDaBarbearia = dataAgendamento.getHour() < 7;
        var depoisDoEncerramentoDaBarbearia = dataAgendamento.getHour() > 18;

        if (domingo || antesDaAberturaDaBarbearia || depoisDoEncerramentoDaBarbearia) {
            throw new ValidacaoException("Agendamento fora do horário de funcionamento da barbearia!");
        }
    }
}
