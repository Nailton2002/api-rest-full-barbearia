package com.api.barbearia.infra.agendamento.validations.agendamento;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.repository.agendamento.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteSemOutroAgendamentoNoDia implements ValidadorAgendamento{

    @Autowired
    private AgendamentoRepository repository;

    public void validar(AgendamentoRequest request){

        var primeiroHorario = request.data().withHour(7);
        var ultimoHorario = request.data().withHour(18);
        var pacientePossuiOutraConsultaNoDia = repository.existsByClienteIdAndDataBetween(request.idCliente(),
            primeiroHorario, ultimoHorario);

        if (pacientePossuiOutraConsultaNoDia){
            throw new ValidacaoException("Cliente já possui um agendamento nesse dia");
        }
    }
}
