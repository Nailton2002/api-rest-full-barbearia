package com.api.barbearia.application.barbeiro.dto;

import com.api.barbearia.domain.endereco.dto.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record BarbeiroDadosAtualizacao(
       @NotNull
       Long id,
       String nome,
       String telefone,
       DadosEndereco endereco) {
}
