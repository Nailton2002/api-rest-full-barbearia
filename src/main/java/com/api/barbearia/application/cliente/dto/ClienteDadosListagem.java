package com.api.barbearia.application.cliente.dto;


import com.api.barbearia.domain.cliente.entity.Cliente;

public record ClienteDadosListagem(
        Long id, Boolean ativo, String nome, String email
) {
    public ClienteDadosListagem(Cliente obj) {

        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail());
    }
}
