package com.api.barbearia.domain.agendamento.validations.agendamento;

import com.api.barbearia.controller.agendamento.dto.AgendamentoDadosCadastro;
import com.api.barbearia.domain.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorClienteAtivo implements ValidadorAgendamento{

    @Autowired
    private ClienteRepository repository;

    public void validar(AgendamentoDadosCadastro dados){

        var clienteEstaAtivo = repository.findAtivoById(dados.idCliente());

        if (!clienteEstaAtivo){
            throw new ValidacaoException("Este cliente esta excluido");
        }
    }

}
