package com.api.barbearia.domain.barbeiro1.service;

import com.api.barbearia.application.barbeiro.dto.BarbeiroDadosCadastrais;
import com.api.barbearia.application.barbeiro.dto.BarbeiroDadosDetalhado;
import com.api.barbearia.application.barbeiro.dto.BarbeiroDadosListagem;
import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.repository.BarbeiroRepository;
import com.api.barbearia.infra.exceptions.validation.ObjectNotFoundExceptionService;
import com.api.barbearia.infra.exceptions.validation.ObjectNotFoundException;
import com.api.barbearia.infra.exceptions.validation.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository repository;

    public Barbeiro salvar(BarbeiroDadosCadastrais dados) {

        boolean emailExiste = repository.existsByEmail(dados.email());
        if (emailExiste){
            throw new ObjectNotFoundExceptionService("Email existe");
        }

        boolean foneExiste = repository.existsByTelefone(dados.telefone());
        if (foneExiste){
            throw new ObjectNotFoundExceptionService("Telefone existe");
        }

        var obj = new Barbeiro(dados);
        obj = repository.save(new Barbeiro(dados));
        return obj;
    }

    public BarbeiroDadosDetalhado buscarPorId(Long id){
        var obj = repository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id));
        return new BarbeiroDadosDetalhado(obj);
    }

    public List<BarbeiroDadosListagem> buscarTodos(){
        List<Barbeiro> list = repository.findAll();
        List<BarbeiroDadosListagem> dados = list.stream().map(b -> new BarbeiroDadosListagem(b)).collect(Collectors.toList());
        return dados;
    }

    public List<BarbeiroDadosDetalhado> findByNome(String nome) {
        List<Barbeiro> list = repository.findByNome(nome);
        List<BarbeiroDadosDetalhado> dados = list.stream().map(b -> new BarbeiroDadosDetalhado(b)).collect(Collectors.toList());
        return dados;
    }

    public Barbeiro atualizar(Long id) {
        if (repository.getReferenceById(id).getAtivo()==true) {
            Optional<Barbeiro> obj = Optional.of(repository.getReferenceById(id));
            return obj.orElseThrow(()-> new ObjectNotFoundException(id));
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void deletar(Long id){
        if (repository.existsById(id) == true) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id);
        }
    }

    public void barbeiroDesativo(Long id){
        var obj = repository.getReferenceById(id);
        if (referencia(id).getAtivo() == true){
            obj.barbeiroDesativo();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    public Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao);
    }
    public Page<Barbeiro> buscarPorAtivoPaginada(Pageable paginacao){
        return repository.findAllByAtivoTrue(paginacao);
    }

    //METODO DE REFERENCIA PARA ATUALIZAR E DELETAR
    public Barbeiro referencia(Long id) {
        Optional<Barbeiro> obj = Optional.of(repository.getReferenceById(id));
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado" + Barbeiro.class));
    }


























}
