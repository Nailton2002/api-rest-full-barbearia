package com.api.barbearia.domain.dto.barbeiro;

import com.api.barbearia.domain.enums.barbeiro.Especialidade;
import com.api.barbearia.domain.dto.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record BarbeiroDadosCadastrais(
        @NotBlank(message = "Nome é obrigatório")
        String nome,
        @Email(message = "Formato do email é inválido")
        @NotBlank(message = "Email é obrigatório")
        String email,
        @NotBlank(message = "Telefone é obrigatório")
        String telefone,
        @NotNull(message = "Especialidade é obrigatória")
        Especialidade especialidade,
        @Valid
        @NotNull(message = "Dados do endereço são obrigatórios")
        DadosEndereco endereco) {
}
