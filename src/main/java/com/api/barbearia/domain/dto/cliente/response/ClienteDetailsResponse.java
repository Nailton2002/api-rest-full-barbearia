package com.api.barbearia.domain.dto.cliente.response;

import com.api.barbearia.domain.entity.Cliente;
import com.api.barbearia.domain.model.Endereco;

public record ClienteDetailsResponse(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        Endereco endereco
) {
    public ClienteDetailsResponse(Cliente obj){
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail(), obj.getTelefone(), obj.getEndereco());
    }

}
