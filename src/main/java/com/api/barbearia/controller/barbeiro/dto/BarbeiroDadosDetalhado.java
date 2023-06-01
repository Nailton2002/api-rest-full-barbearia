package com.api.barbearia.controller.barbeiro.dto;

import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import com.api.barbearia.endereco.model.Endereco;

public record BarbeiroDadosDetalhado(
        Long id,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public BarbeiroDadosDetalhado(Barbeiro obj){
        this(obj.getId(), obj.getNome(), obj.getEmail(), obj.getTelefone(), obj.getEspecialidade(),
             obj.getEndereco());
    }

}
