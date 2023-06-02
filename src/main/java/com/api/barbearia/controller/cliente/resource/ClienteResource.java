package com.api.barbearia.controller.cliente.resource;

import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosAtualizacao;
import com.api.barbearia.controller.barbeiro.dto.BarbeiroDadosDetalhado;
import com.api.barbearia.controller.cliente.dto.ClienteDadosAtualizacao;
import com.api.barbearia.controller.cliente.dto.ClienteDadosCadastrais;
import com.api.barbearia.controller.cliente.dto.ClienteDadosDetalhado;
import com.api.barbearia.domain.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
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
}
