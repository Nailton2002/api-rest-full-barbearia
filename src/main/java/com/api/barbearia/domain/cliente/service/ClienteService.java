package com.api.barbearia.domain.cliente.service;

import com.api.barbearia.controller.cliente.dto.ClienteDadosAtualizacao;
import com.api.barbearia.controller.cliente.dto.ClienteDadosCadastrais;

import com.api.barbearia.domain.cliente.entity.Cliente;
import com.api.barbearia.domain.cliente.repository.ClienteRepository;
import com.api.barbearia.domain.cliente.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    //METODO DE REFERENCIA PARA ATUALIZAR E DELETAR
    public Cliente atualizar(Long id) {
        Optional<Cliente> obj = Optional.of(repository.getReferenceById(id));
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + Cliente.class));
    }
    public Cliente salvar (ClienteDadosCadastrais dados) {
        var obj = new Cliente(dados);
        obj = repository.save(new Cliente(dados));
        return obj;
    }

}
