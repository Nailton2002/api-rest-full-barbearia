package com.api.barbearia.controller.barbeiro.resource;

import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosAtualizacao;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosCadastrais;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosListagem;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosDetalhado;
import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.repository.BarbeiroRepository;
import com.api.barbearia.domain.barbeiro.service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/barbeiros")
public class BarbeiroResource {

    @Autowired
    private BarbeiroService service;

    @Autowired
    private BarbeiroRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid BarbeiroDadosCadastrais dados, UriComponentsBuilder uriComponentsBuilder){
        var obj = service.salvar(dados);
        var uri = uriComponentsBuilder.path("/barbeiros/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new BarbeiroDadosDetalhado(obj));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atulizar(@RequestBody @Valid BarbeiroDadosAtualizacao dados){
        var obj = service.atualizar(dados.id());
        obj.atualizarInformacoes(dados);
        return ResponseEntity.ok(new BarbeiroDadosDetalhado(obj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroDadosDetalhado> buscarPorId(@PathVariable Long id){
        BarbeiroDadosDetalhado dados = service.buscarPorId(id);
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroDadosListagem>> listar(){
        List<BarbeiroDadosListagem> dadosListagems = service.buscarTodos();
        return ResponseEntity.ok().body(dadosListagems);
    }

    @GetMapping("/porNome")
    public ResponseEntity<List<BarbeiroDadosDetalhado>> buscarPorNome(@RequestParam(name = "nome") String nome){
        List<BarbeiroDadosDetalhado> dados = service.findByNome(nome);
        return ResponseEntity.ok().body(dados);
    }















}


































