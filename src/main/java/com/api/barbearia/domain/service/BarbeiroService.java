package com.api.barbearia.domain.service;

import com.api.barbearia.domain.dto.barbeiro.request.BarbeiroRequest;
import com.api.barbearia.domain.dto.barbeiro.request.BarbeiroUpRequest;
import com.api.barbearia.domain.entity.Barbeiro;
import com.api.barbearia.domain.repository.BarbeiroRepository;
import com.api.barbearia.infra.exceptions.validation.ObjectNotFoundExceptionService;
import com.api.barbearia.infra.exceptions.validation.ObjectNotFoundException;
import com.api.barbearia.infra.exceptions.validation.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository repository;

    public Barbeiro salvar(BarbeiroRequest dados) {
        boolean emailExiste = repository.existsByEmail(dados.email());
        if (emailExiste) {
            throw new ObjectNotFoundExceptionService("Email existe");
        }
        boolean foneExiste = repository.existsByTelefone(dados.telefone());
        if (foneExiste) {
            throw new ObjectNotFoundExceptionService("Telefone existe");
        }
        var obj = new Barbeiro(dados);
        obj = repository.save(new Barbeiro(dados));
        return obj;
    }

    public Barbeiro buscarPorId(Long id) {
        var obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
        return obj;
    }

    public List<Barbeiro> buscarTodos() {
        List<Barbeiro> list = repository.findAll();
        return list;
    }

    public List<Barbeiro> findByNome(String nome) {
        List<Barbeiro> list = repository.findByNome(nome);
        return list;
    }

    public Barbeiro atualizar(BarbeiroUpRequest upRequest) {
        var objId = repository.getReferenceById(upRequest.id());
        objId.atualizarInformacoes(upRequest);
        return repository.save(objId);
    }

    public void deletar(Long id) {
        if (repository.existsById(id) == true) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void barbeiroDesativo(Long id) {
        var obj = repository.getReferenceById(id);
        if (referencia(id).getAtivo() == true) {
            obj.barbeiroDesativo();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }

    public Page<Barbeiro> buscarPorAtivoPaginada(Pageable paginacao) {return repository.findAllByAtivoTrue(paginacao);}

    //METODO DE REFERENCIA PARA ATUALIZAR E DELETAR
    public Barbeiro referencia(Long id) {
        Optional<Barbeiro> obj = Optional.of(repository.getReferenceById(id));
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + Barbeiro.class));
    }

}
