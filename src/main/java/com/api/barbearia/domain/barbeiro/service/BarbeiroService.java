package com.api.barbearia.domain.barbeiro.service;

import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosCadastrais;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosListagem;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosPorId;
import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.repository.BarbeiroRepository;
import com.api.barbearia.domain.barbeiro.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository repository;

    public Barbeiro salvar(BarbeiroDadosCadastrais dados){
        var obj = new Barbeiro(dados);
        obj = repository.save(new Barbeiro(dados));
        return obj;
    }

    public Barbeiro atualizar(Long id) {
        if (repository.existsById(id)) {
            Optional<Barbeiro> obj = Optional.of(repository.getReferenceById(id));
            return obj.orElseThrow(()-> new ObjectNotFoundException(id));
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public BarbeiroDadosPorId buscarPorId(Long id){
        var obj = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id));
        return new BarbeiroDadosPorId(obj);
    }

    public List<BarbeiroDadosListagem> buscarTodos(){
        List<Barbeiro> list = repository.findAll();
        List<BarbeiroDadosListagem> dados = list.stream().map(b -> new BarbeiroDadosListagem(b)).collect(Collectors.toList());
        return dados;
    }


























}
