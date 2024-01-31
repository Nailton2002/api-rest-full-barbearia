package com.api.barbearia.application.resource.cliente;

import com.api.barbearia.domain.dto.cliente.request.ClienteUpdateRequest;
import com.api.barbearia.domain.dto.cliente.request.ClienteSaveRequest;
import com.api.barbearia.domain.dto.cliente.response.ClienteDetailsResponse;
import com.api.barbearia.domain.dto.cliente.response.ClienteListResponse;
import com.api.barbearia.domain.entity.cliente.Cliente;
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
import java.util.stream.Collectors;

@RestController
@Profile("dev")
@RequestMapping("/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService service;

    @Transactional
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid ClienteSaveRequest dados, UriComponentsBuilder uriComponentsBuilder) {
        var obj = service.salvar(dados);
        var uri = uriComponentsBuilder.path("/clientes/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDetailsResponse(obj));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atulizar(@PathVariable Long id, @RequestBody @Valid ClienteUpdateRequest request) {
        var obj = service.atualizar(id, request);
        return ResponseEntity.ok(new ClienteDetailsResponse(obj));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDetailsResponse> buscarPorId(@PathVariable Long id) {
        Cliente obj = service.buscarPorId(id);
        return ResponseEntity.ok().body(new ClienteDetailsResponse(obj));
    }

    @GetMapping("/porNomes")
    public ResponseEntity<List<ClienteDetailsResponse>> buscarPorNome(@RequestParam(name = "nome") String nome) {
        List<ClienteDetailsResponse> responseList = service.buscarPorNome(nome).stream().map(c -> new ClienteDetailsResponse(c)).collect(Collectors.toList());
        return ResponseEntity.ok().body(responseList);
    }

    @GetMapping
    public ResponseEntity<List<ClienteListResponse>> listar() {
        List<ClienteListResponse> dadosList = service.buscarTodos().stream().map(c -> new ClienteListResponse(c)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dadosList);
    }

    @GetMapping(value = "/paginados")
    public ResponseEntity<Page<ClienteListResponse>> buscarPorAtivoPaginada(@PageableDefault(size = 5, sort = {"nome"}) Pageable paginacao) {
        var page = service.buscarPorAtivoPaginada(paginacao).map(ClienteListResponse::new);
        return ResponseEntity.ok(page);
    }

    @Transactional
    @DeleteMapping("/desativos/{id}")
    public ResponseEntity clienteDesativo(@PathVariable Long id) {
        service.clienteDesativo(id);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }


}
