package com.api.barbearia.domain.barbeiro.service;

import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosCadastrais;
import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository repository;

    public Barbeiro salvar(BarbeiroDadosCadastrais dados){
        Barbeiro obj = new Barbeiro(dados);
        obj = repository.save(new Barbeiro(dados));
        return obj;
    }
}
