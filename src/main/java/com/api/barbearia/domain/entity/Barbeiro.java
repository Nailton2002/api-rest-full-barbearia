package com.api.barbearia.domain.entity;

import com.api.barbearia.domain.dto.barbeiro.request.BarbeiroUpRequest;
import com.api.barbearia.domain.dto.barbeiro.request.BarbeiroRequest;
import com.api.barbearia.domain.enums.barbeiro.Especialidade;
import com.api.barbearia.domain.model.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity(name = "Barbeiro")
@Table(name = "barbeiros")
@EqualsAndHashCode(of = "id")
public class Barbeiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Barbeiro(BarbeiroRequest dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(BarbeiroUpRequest dados) {
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
