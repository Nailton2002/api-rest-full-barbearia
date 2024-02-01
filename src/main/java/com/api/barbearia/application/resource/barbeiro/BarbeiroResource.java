package com.api.barbearia.application.resource.barbeiro;

import com.api.barbearia.domain.dto.barbeiro.request.BarbeiroUpRequest;
import com.api.barbearia.domain.dto.barbeiro.request.BarbeiroRequest;
import com.api.barbearia.domain.dto.barbeiro.response.BarbeiroResponse;
import com.api.barbearia.domain.dto.barbeiro.response.BarbeiroListResponse;
import com.api.barbearia.domain.entity.barbeiro.Barbeiro;
import com.api.barbearia.domain.service.barbeiro.BarbeiroService;
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
import java.util.stream.Collectors;

@RestController
@Profile("dev")
@RequestMapping("/barbeiros")
public class BarbeiroResource {

    @Autowired
    private BarbeiroService service;

    @Transactional
    @PostMapping
    public ResponseEntity salvar(@RequestBody @Valid BarbeiroRequest dados, UriComponentsBuilder uriComponentsBuilder){
        var obj = service.salvar(dados);
        var uri = uriComponentsBuilder.path("/barbeiros/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new BarbeiroResponse(obj));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atulizar(@PathVariable Long id, @RequestBody @Valid BarbeiroUpRequest upRequest){
        var obj = service.atualizar(upRequest);
        return ResponseEntity.ok(new BarbeiroResponse(obj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroResponse> buscarPorId(@PathVariable Long id){
        var obj = service.buscarPorId(id);
        return ResponseEntity.ok().body(new BarbeiroResponse(obj));
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<BarbeiroResponse>> buscarPorNome(@RequestParam(name = "nome") String nome){
        List<BarbeiroResponse> dados = service.findByNome(nome).stream().map(b -> new BarbeiroResponse(b)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dados);
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroListResponse>> listar(){
        List<BarbeiroListResponse> responseList = service.buscarTodos().stream().map(b -> new BarbeiroListResponse(b)).collect(Collectors.toList());;
        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<BarbeiroListResponse>> buscarPorAtivoPaginada(@PageableDefault(size = 5,
    sort = {"nome"}) Pageable paginacao){
        var page = service.findAllByAtivoTrue(paginacao).map(BarbeiroListResponse::new);
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


































