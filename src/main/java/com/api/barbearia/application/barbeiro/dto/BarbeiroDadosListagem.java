package com.api.barbearia.application.barbeiro.dto;

import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;

public record BarbeiroDadosListagem(
        Long id, Boolean ativo, String nome, String email, Especialidade especialidade
) {
    public BarbeiroDadosListagem(Barbeiro obj) {
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail(), obj.getEspecialidade());
    }
}
