package com.api.barbearia.controller.cliente.dto;

import com.api.barbearia.domain.cliente.entity.Cliente;
import com.api.barbearia.domain.endereco.model.Endereco;

public record ClienteDadosDetalhado(
        Long id,
        Boolean ativo,
        String nome,
        String email,
        String telefone,
        Endereco endereco
) {
    public ClienteDadosDetalhado(Cliente obj){
        this(obj.getId(), obj.getAtivo(), obj.getNome(), obj.getEmail(), obj.getTelefone(), obj.getEndereco());
    }

}
