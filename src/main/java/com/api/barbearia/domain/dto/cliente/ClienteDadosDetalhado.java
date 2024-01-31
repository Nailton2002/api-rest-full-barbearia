package com.api.barbearia.domain.dto.cliente;

import com.api.barbearia.domain.entity.cliente.Cliente;
import com.api.barbearia.domain.model.Endereco;

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
