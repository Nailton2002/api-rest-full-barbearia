package com.api.barbearia.domain.dto.barbeiro.response;

import com.api.barbearia.domain.entity.Barbeiro;
import com.api.barbearia.domain.enums.barbeiro.Especialidade;
import com.api.barbearia.domain.model.Endereco;

public record BarbeiroResponse(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        Especialidade especialidade,
        Endereco endereco
) {
    public BarbeiroResponse(Barbeiro obj) {
        this(obj.getId(),
             obj.getAtivo(),
             obj.getNome(),
             obj.getEmail(),
             obj.getTelefone(),
             obj.getEspecialidade(),
             obj.getEndereco());
    }

}
