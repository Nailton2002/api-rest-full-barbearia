package com.api.barbearia.domain.cliente.entity;

import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosAtualizacao;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosCadastrais;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import com.api.barbearia.endereco.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Barbeiro")
@Table(name = "tb_barbeiros")
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

    public Cliente(BarbeiroDadosCadastrais dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(BarbeiroDadosAtualizacao dados) {
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

    public void barbeiroDesativo() {
        this.ativo = false;
    }
}