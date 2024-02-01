package com.api.barbearia.domain.dto.barbeiro.request;

import com.api.barbearia.domain.dto.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record BarbeiroUpRequest(
       @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
