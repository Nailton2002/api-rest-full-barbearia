package com.api.barbearia.controller.cliente.resource;

import com.api.barbearia.controller.cliente.dto.ClienteDadosCadastrais;
import com.api.barbearia.controller.cliente.dto.ClienteDadosDetalhado;
import com.api.barbearia.domain.cliente.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
