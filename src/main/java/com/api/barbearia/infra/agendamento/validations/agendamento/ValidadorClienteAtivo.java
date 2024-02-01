package com.api.barbearia.infra.agendamento.validations.agendamento;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.repository.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteAtivo implements ValidadorAgendamento{

    @Autowired
    private ClienteRepository repository;

    public void validar(AgendamentoRequest request){

        var clienteEstaAtivo = repository.findAtivoById(request.idCliente());

        if (!clienteEstaAtivo){
            throw new ValidacaoException("Este cliente esta excluido");
        }
    }

}
