package com.api.barbearia.controller.barbeiro.dto;

import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;

public record BarbeiroDadosListagem(
        Long id, String nome, String email, Especialidade especialidade
) {
    public BarbeiroDadosListagem(Barbeiro obj) {
        this(obj.getId(), obj.getNome(), obj.getEmail(), obj.getEspecialidade());
    }
}
