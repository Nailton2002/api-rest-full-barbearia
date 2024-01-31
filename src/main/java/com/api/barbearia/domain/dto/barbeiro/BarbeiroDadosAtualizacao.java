package com.api.barbearia.domain.dto.barbeiro;

import com.api.barbearia.domain.dto.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record BarbeiroDadosAtualizacao(
       @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco) {
}
