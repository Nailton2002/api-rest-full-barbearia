package com.api.barbearia.controller.cliente.dto;

import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import com.api.barbearia.endereco.model.Endereco;

public record ClienteDadosDetalhado(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        Endereco endereco
) {
    public ClienteDadosDetalhado(Barbeiro obj){
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail(), obj.getTelefone(), obj.getEndereco());
    }

}
