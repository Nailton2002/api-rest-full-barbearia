package com.api.barbearia.domain.cliente.service;

import com.api.barbearia.controller.cliente.dto.ClienteDadosCadastrais;
import com.api.barbearia.controller.cliente.dto.ClienteDadosDetalhado;
import com.api.barbearia.controller.cliente.dto.ClienteDadosListagem;
import com.api.barbearia.domain.cliente.entity.Cliente;
import com.api.barbearia.domain.cliente.repository.ClienteRepository;
import com.api.barbearia.infra.exception.ObjectNotFoundException;
import com.api.barbearia.infra.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    public Cliente salvar (ClienteDadosCadastrais dados) {
        var obj = new Cliente(dados);
        obj = repository.save(new Cliente(dados));
        return obj;
    }

    public Cliente atualizar(Long id) {
        if (repository.getReferenceById(id).getAtivo()==true) {
            Optional<Cliente> obj = Optional.of(repository.getReferenceById(id));
            return obj.orElseThrow(()-> new ObjectNotFoundException(id));
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public ClienteDadosDetalhado buscarPorId(Long id){
        var obj = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id));
        return new ClienteDadosDetalhado(obj);
    }

    public List<ClienteDadosDetalhado> buscarPorNome(String nome){
        List<Cliente> objList = repository.findByNome(nome);
        List<ClienteDadosDetalhado> dadosList = objList.stream().map(c -> new ClienteDadosDetalhado(c)).collect(Collectors.toList());
        return dadosList;
    }
    public List<ClienteDadosListagem> buscarTodos(){
        List<Cliente> objList = repository.findAll();
        List<ClienteDadosListagem> dadosList = objList.stream().map(c -> new ClienteDadosListagem(c)).collect(Collectors.toList());
        return dadosList;
    }

    public Page<Cliente> buscarPorAtivoPaginada(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao);
    }

    public void clienteDesativo(Long id){
        var obj = repository.getReferenceById(id);
        if (referencia(id).getAtivo() == true){
            obj.clienteDesativo();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public void deletar(Long id){
        if (repository.existsById(id) == true) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    //METODO DE REFERENCIA PARA ATUALIZAR E DELETAR
    public Cliente referencia(Long id) {
        Optional<Cliente> obj = Optional.of(repository.getReferenceById(id));
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + Cliente.class));
    }




















}
