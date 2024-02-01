package com.api.barbearia.application.resource;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;
import com.api.barbearia.domain.dto.agendamento.request.AgendamentoCancelamentoRequeste;
import com.api.barbearia.domain.dto.agendamento.response.AgendamentoResponse;
import com.api.barbearia.domain.service.AgendamentoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Profile("dev")
@RequestMapping("/agendamentos")
public class AgendaController {

    @Autowired
    private AgendamentoService service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid AgendamentoRequest request) {
        var dto = service.agendar(request);
        return ResponseEntity.ok(dto);
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity cancelar(@RequestBody @Valid AgendamentoCancelamentoRequeste requeste) {
        service.cancelar(requeste);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AgendamentoResponse>> verTodaAgenda(){
        List<AgendamentoResponse> listDados = service.verTodaAgenda();
        return ResponseEntity.ok().body(listDados);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoResponse> verAgenda(@PathVariable Long id){
        AgendamentoResponse dados = service.verAgenda(id);
        return ResponseEntity.ok().body(dados);
    }

}
