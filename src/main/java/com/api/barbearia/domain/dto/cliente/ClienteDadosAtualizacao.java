package com.api.barbearia.domain.dto.cliente;

import com.api.barbearia.domain.dto.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record ClienteDadosAtualizacao(
       @NotNull
       Long id,
       String nome,
       String telefone,
       DadosEndereco endereco) {
}
