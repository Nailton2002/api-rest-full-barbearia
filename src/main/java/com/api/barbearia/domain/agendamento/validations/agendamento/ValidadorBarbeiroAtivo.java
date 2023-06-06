package com.api.barbearia.domain.agendamento.validations.agendamento;

import com.api.barbearia.application.agendamento.dto.AgendamentoDadosCadastro;
import com.api.barbearia.domain.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.barbeiro.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorBarbeiroAtivo implements ValidadorAgendamento{

    @Autowired
    private BarbeiroRepository repository;

    public void validar(AgendamentoDadosCadastro dados){

        if (dados.idBarbeiro() == null){
            return;
        }

        var medicoEstaAtivo = repository.findAtivoById(dados.idBarbeiro());

        if (!medicoEstaAtivo){
            throw new ValidacaoException("Não pode ser agendada com este barbeiro!");
        }
    }
}
