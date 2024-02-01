package com.api.barbearia.infra.agendamento.validations.agendamento;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorBarbeiroAtivo implements ValidadorAgendamento{

    @Autowired
    private BarbeiroRepository repository;

    public void validar(AgendamentoRequest request){

        if (request.idBarbeiro() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(request.idBarbeiro());

        if (!medicoEstaAtivo){
            throw new ValidacaoException("Não pode ser agendada com este barbeiro!");
        }
    }
}
