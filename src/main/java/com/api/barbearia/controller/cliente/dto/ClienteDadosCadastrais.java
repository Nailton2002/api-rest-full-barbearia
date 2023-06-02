package com.api.barbearia.controller.cliente.dto;

import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import com.api.barbearia.endereco.dto.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDadosCadastrais(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @Email(message = "Formato do email é inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,
        @NotBlank(message = "Telefone é obrigatório")
        String telefone,
        @Valid
        @NotNull(message = "Dados do endereço são obrigatórios")
        DadosEndereco endereco) {
}
