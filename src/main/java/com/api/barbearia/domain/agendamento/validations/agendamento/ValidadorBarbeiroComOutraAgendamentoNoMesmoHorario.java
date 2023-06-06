package com.api.barbearia.domain.agendamento.validations.agendamento;

import com.api.barbearia.application.agendamento.dto.AgendamentoDadosCadastro;
import com.api.barbearia.domain.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.agendamento.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorBarbeiroComOutraAgendamentoNoMesmoHorario implements ValidadorAgendamento{

    @Autowired
    private AgendamentoRepository repository;

    public void validar(AgendamentoDadosCadastro dados){

        var barbeiroPossuiOutraConsultaNoMesmoHorario = repository.existsByBarbeiroIdAndData(dados.idBarbeiro(),
            dados.data());

        if (barbeiroPossuiOutraConsultaNoMesmoHorario){
            throw new ValidacaoException("Barbeiro já possui outra agendamento nesse mesmo horário!");
        }
    }

}
