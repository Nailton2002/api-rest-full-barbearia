package com.api.barbearia.application.resource.cliente;

import com.api.barbearia.domain.dto.cliente.ClienteDadosAtualizacao;
import com.api.barbearia.domain.dto.cliente.ClienteDadosCadastrais;
import com.api.barbearia.domain.dto.cliente.ClienteDadosDetalhado;
import com.api.barbearia.domain.dto.cliente.ClienteDadosListagem;
import com.api.barbearia.domain.service.cliente.ClienteService;

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
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @Transactional
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ClienteDadosCadastrais dados, UriComponentsBuilder uriComponentsBuilder){
        var obj = service.salvar(dados);
        var uri = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDadosDetalhado(obj));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atulizar(@RequestBody @Valid ClienteDadosAtualizacao dados){
        var obj = service.atualizar(dados.id());
        obj.atualizarInformacoes(dados);
        return ResponseEntity.ok(new ClienteDadosDetalhado(obj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDadosDetalhado> buscarPorId(@PathVariable Long id){
        ClienteDadosDetalhado dados = service.buscarPorId(id);
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<ClienteDadosDetalhado>> buscarPorNome(@RequestParam(name = "nome") String nome){
        List<ClienteDadosDetalhado> dados = service.buscarPorNome(nome);
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDadosListagem>> listar(){
        List<ClienteDadosListagem> dadosList = service.buscarTodos();
        return ResponseEntity.ok().body(dadosList);
    }

    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<ClienteDadosListagem>> buscarPorAtivoPaginada(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao){
        var page = service.buscarPorAtivoPaginada(paginacao).map(ClienteDadosListagem::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @DeleteMapping("/desativos/{id}")
    public ResponseEntity clienteDesativo(@PathVariable Long id){
        service.clienteDesativo(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }























}
