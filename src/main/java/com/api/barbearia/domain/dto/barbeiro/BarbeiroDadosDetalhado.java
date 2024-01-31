package com.api.barbearia.domain.dto.barbeiro;

import com.api.barbearia.domain.entity.barbeiro.Barbeiro;
import com.api.barbearia.domain.enums.barbeiro.Especialidade;
import com.api.barbearia.domain.model.Endereco;

public record BarbeiroDadosDetalhado(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public BarbeiroDadosDetalhado(Barbeiro obj){
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail(), obj.getTelefone(), obj.getEspecialidade(),
             obj.getEndereco());
    }

}
