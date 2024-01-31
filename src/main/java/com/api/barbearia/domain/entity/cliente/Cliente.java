package com.api.barbearia.domain.entity.cliente;

import com.api.barbearia.domain.dto.cliente.ClienteDadosAtualizacao;
import com.api.barbearia.domain.dto.cliente.ClienteDadosCadastrais;
import com.api.barbearia.domain.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
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

    public Cliente(ClienteDadosCadastrais dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(ClienteDadosAtualizacao dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco());
        }
    }

    public void clienteDesativo() {
        this.ativo = false;
    }
}
