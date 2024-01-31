package com.api.barbearia.domain.dto.cliente.response;


import com.api.barbearia.domain.entity.cliente.Cliente;

public record ClienteListResponse(
        Long id, Boolean ativo, String nome, String email
) {
    public ClienteListResponse(Cliente obj) {
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail());
    }
}
