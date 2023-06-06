package com.api.barbearia.application.agendamento.resource;

import com.api.barbearia.application.agendamento.dto.AgendamentoDadosCadastro;
import com.api.barbearia.application.agendamento.dto.AgendamentoDadosCancelamento;
import com.api.barbearia.domain.agendamento.service.AgendamentoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Profile("dev")
@RequestMapping("/agendamentos")
public class AgendaController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoDadosCadastro dados) {
        var dto = service.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity cancelar(@RequestBody @Valid AgendamentoDadosCancelamento dados) {
        service.cancelar(dados);
        return ResponseEntity.noContent().build();
    }

}
