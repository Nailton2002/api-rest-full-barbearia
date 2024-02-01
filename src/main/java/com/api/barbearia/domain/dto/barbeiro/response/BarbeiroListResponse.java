package com.api.barbearia.domain.dto.barbeiro.response;

import com.api.barbearia.domain.entity.barbeiro.Barbeiro;
import com.api.barbearia.domain.enums.barbeiro.Especialidade;
import lombok.Builder;

public record BarbeiroListResponse(Long id, Boolean ativo, String nome, String email, Especialidade especialidade) {
    public BarbeiroListResponse(Barbeiro obj) {
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail(), obj.getEspecialidade());
    }

}
