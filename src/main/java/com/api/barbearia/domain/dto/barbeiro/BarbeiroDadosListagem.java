package com.api.barbearia.domain.dto.barbeiro;

import com.api.barbearia.domain.entity.barbeiro.Barbeiro;
import com.api.barbearia.domain.enums.barbeiro.Especialidade;

public record BarbeiroDadosListagem(
        Long id, Boolean ativo, String nome, String email, Especialidade especialidade
) {
    public BarbeiroDadosListagem(Barbeiro obj) {
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail(), obj.getEspecialidade());
    }
}
