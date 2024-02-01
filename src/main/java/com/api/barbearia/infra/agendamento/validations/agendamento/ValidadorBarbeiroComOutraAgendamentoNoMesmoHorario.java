package com.api.barbearia.infra.agendamento.validations.agendamento;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.repository.agendamento.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorBarbeiroComOutraAgendamentoNoMesmoHorario implements ValidadorAgendamento{

    @Autowired
    private AgendamentoRepository repository;

    public void validar(AgendamentoRequest request){

        var barbeiroPossuiOutraConsultaNoMesmoHorario = repository.existsByBarbeiroIdAndData(request.idBarbeiro(),
            request.data());

        if (barbeiroPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Barbeiro já possui outra agendamento nesse mesmo horário!");
        }
    }

}
