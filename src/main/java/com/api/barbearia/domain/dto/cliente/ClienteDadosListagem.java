package com.api.barbearia.domain.dto.cliente;


import com.api.barbearia.domain.entity.cliente.Cliente;

public record ClienteDadosListagem(
        Long id, Boolean ativo, String nome, String email
) {
    public ClienteDadosListagem(Cliente obj) {

        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail());
    }
}
