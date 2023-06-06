package com.api.barbearia.application.cliente.dto;

import com.api.barbearia.domain.endereco.dto.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record ClienteDadosAtualizacao(
       @NotNull
       Long id,
       String nome,
       String telefone,
       DadosEndereco endereco) {
}
