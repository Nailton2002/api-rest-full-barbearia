package com.api.barbearia.domain.dto.cliente.request;

import com.api.barbearia.domain.dto.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record ClienteUpdateRequest(
       String nome,
       String telefone,
       DadosEndereco endereco) {
}
