package com.api.barbearia.controller.cliente.dto;

import com.api.barbearia.endereco.dto.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record ClienteDadosAtualizacao(
       @NotNull
       Long id,
       String nome,
       String telefone,
       DadosEndereco endereco) {
}
