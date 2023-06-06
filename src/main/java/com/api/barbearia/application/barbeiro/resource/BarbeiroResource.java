package com.api.barbearia.application.barbeiro.resource;

import com.api.barbearia.application.barbeiro.dto.BarbeiroDadosAtualizacao;
import com.api.barbearia.application.barbeiro.dto.BarbeiroDadosCadastrais;
import com.api.barbearia.application.barbeiro.dto.BarbeiroDadosDetalhado;
import com.api.barbearia.application.barbeiro.dto.BarbeiroDadosListagem;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@Profile("dev")
@RequestMapping("/barbeiros")
public class BarbeiroResource {

    @Autowired
    private com.api.barbearia.domain.barbeiro1.service.BarbeiroService service;

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

    @GetMapping("/porNomes")
    public ResponseEntity<List<BarbeiroDadosDetalhado>> buscarPorNome(@RequestParam(name = "nome") String nome){
        List<BarbeiroDadosDetalhado> dados = service.findByNome(nome);
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroDadosListagem>> listar(){
        List<BarbeiroDadosListagem> dadosListagems = service.buscarTodos();
        return ResponseEntity.ok().body(dadosListagems);
    }

    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<BarbeiroDadosListagem>> buscarPorAtivoPaginada(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao){
        var page = service.findAllByAtivoTrue(paginacao).map(BarbeiroDadosListagem::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/desativos/{id}")
    public ResponseEntity barbeiroDesativo(@PathVariable Long id){
         service.barbeiroDesativo(id);
        return ResponseEntity.noContent().build();
    }













}


































