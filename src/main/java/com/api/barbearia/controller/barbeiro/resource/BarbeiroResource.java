package com.api.barbearia.controller.barbeiro.resource;

import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosAtualizacao;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosCadastrais;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosListagem;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosPorId;
import com.api.barbearia.domain.barbeiro.service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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

    @Transactional
    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid BarbeiroDadosCadastrais dados, UriComponentsBuilder uriComponentsBuilder){
        var obj = service.salvar(dados);
        var uri = uriComponentsBuilder.path("/barbeiros/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new BarbeiroDadosPorId(obj));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atulizar(@RequestBody @Valid BarbeiroDadosAtualizacao dados){
        var obj = service.atualizar(dados.id());
        obj.atualizarInformacoes(dados);
        return ResponseEntity.ok(new BarbeiroDadosPorId(obj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroDadosPorId> buscarPorId(@PathVariable Long id){
        BarbeiroDadosPorId dados = service.buscarPorId(id);
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroDadosListagem>> listar(){
        List<BarbeiroDadosListagem> dadosListagems = service.buscarTodos();
        return ResponseEntity.ok().body(dadosListagems);
    }




}


































