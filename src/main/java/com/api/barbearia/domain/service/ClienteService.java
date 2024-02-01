package com.api.barbearia.domain.service;

import com.api.barbearia.domain.dto.cliente.request.ClienteSaveRequest;
import com.api.barbearia.domain.dto.cliente.request.ClienteUpdateRequest;
import com.api.barbearia.domain.entity.Cliente;
import com.api.barbearia.domain.repository.ClienteRepository;
import com.api.barbearia.infra.exceptions.validation.ObjectNotFoundExceptionService;
import com.api.barbearia.infra.exceptions.validation.ObjectNotFoundException;
import com.api.barbearia.infra.exceptions.validation.ResourceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;


    public Cliente salvar(ClienteSaveRequest dados) {

        boolean emailExiste = repository.existsByEmail(dados.email());
        if (emailExiste) {
            throw new ObjectNotFoundExceptionService("Email existe na base de dados!");
        }
        boolean foneExiste = repository.existsByTelefone(dados.telefone());
        if (foneExiste) {
            throw new ObjectNotFoundExceptionService("Telefone existe na base de dados!");
        }
        var obj = new Cliente(dados);
        obj = repository.save(new Cliente(dados));
        return obj;
    }

    public Cliente atualizar(Long id, @Valid ClienteUpdateRequest request) {
        var obj = repository.getReferenceById(id);
        obj.atualizarInformacoes(request);
        return repository.save(obj);
    }

    public Cliente buscarPorId(Long id) {
        var obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        return obj;
    }

    public List<Cliente> buscarPorNome(String nome) {
        List<Cliente> objList = repository.findByNome(nome);
        return objList;
    }

    public List<Cliente> buscarTodos() {
        List<Cliente> objList = repository.findAll();
        return objList;
    }

    public Page<Cliente> buscarPorAtivoPaginada(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public void clienteDesativo(Long id) {
        var obj = repository.getReferenceById(id);
        if (referencia(id).getAtivo() == true) {
            obj.clienteDesativo();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public void deletar(Long id) {
        if (repository.existsById(id) == true) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    //METODO DE REFERENCIA PARA DELETAR
    public Cliente referencia(Long id) {
        Optional<Cliente> obj = Optional.of(repository.getReferenceById(id));
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + Cliente.class));
    }

}
