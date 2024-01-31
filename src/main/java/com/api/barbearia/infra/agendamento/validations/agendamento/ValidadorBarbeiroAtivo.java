package com.api.barbearia.infra.agendamento.validations.agendamento;

import com.api.barbearia.domain.dto.agendamento.AgendamentoDadosCadastro;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.repository.barbeiro.BarbeiroRepository;
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
