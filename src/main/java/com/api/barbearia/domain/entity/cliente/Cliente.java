package com.api.barbearia.domain.entity.cliente;

import com.api.barbearia.domain.dto.cliente.request.ClienteUpdateRequest;
import com.api.barbearia.domain.dto.cliente.request.ClienteSaveRequest;
import com.api.barbearia.domain.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "Cliente")
@Table(name = "clientes")
@EqualsAndHashCode(of = "id")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Cliente(ClienteSaveRequest request) {
        this.ativo = true;
        this.nome = request.nome();
        this.email = request.email();
        this.telefone = request.telefone();
        this.endereco = new Endereco(request.endereco());
    }

    public void atualizarInformacoes(ClienteUpdateRequest request) {
        if (request.nome() != null) {
            this.nome = request.nome();
        }
        if (request.telefone() != null) {
            this.telefone = request.telefone();
        }
        if (request.endereco() != null) {
            this.endereco.atualizarInformacoes(request.endereco());
        }
    }

    public void clienteDesativo() {
        this.ativo = false;
    }
}
