package com.api.barbearia.domain.service.agendamento;

import com.api.barbearia.domain.dto.agendamento.request.AgendamentoRequest;
import com.api.barbearia.domain.dto.agendamento.request.AgendamentoCancelamentoRequeste;
import com.api.barbearia.domain.dto.agendamento.response.AgendamentoResponse;
import com.api.barbearia.domain.entity.agendamento.Agendamento;
import com.api.barbearia.infra.agendamento.exception.ValidacaoException;
import com.api.barbearia.domain.repository.agendamento.AgendamentoRepository;
import com.api.barbearia.infra.agendamento.validations.agendamento.ValidadorAgendamento;
import com.api.barbearia.infra.agendamento.validations.cancelamentos.ValidadorCancelamentoDeAgendamento;
import com.api.barbearia.domain.entity.barbeiro.Barbeiro;
import com.api.barbearia.domain.repository.barbeiro.BarbeiroRepository;
import com.api.barbearia.domain.repository.cliente.ClienteRepository;
import com.api.barbearia.infra.exceptions.validation.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {
    @Autowired
    private AgendamentoRepository agendamentoRepository;
    @Autowired
    private BarbeiroRepository barbeiroRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private List<ValidadorAgendamento> validadores;
    //USANDO O PADRÃO DE PROJETO -> Strategy
    @Autowired
    private List<ValidadorCancelamentoDeAgendamento> validadoresCancelamento;

    public void cancelar(AgendamentoCancelamentoRequeste request) {

        if (!agendamentoRepository.existsById(request.idAgendamento())) {
            throw new ValidacaoException("Id do agendamento informado não existe!");
        }
        validadoresCancelamento.forEach(v -> v.validar(request));

        var consulta = agendamentoRepository.getReferenceById(request.idAgendamento());
        consulta.cancelar(request.motivo());
    }

    public AgendamentoResponse agendar(AgendamentoRequest request) {

        if (!clienteRepository.existsById(request.idCliente())) {
            throw new ValidacaoException("Id do cliente informado não existe!");
        }
        if (request.idBarbeiro() != null && !barbeiroRepository.existsById(request.idBarbeiro())) {
            throw new ValidacaoException("Id do barbeiro informado não existe!");
        }
        validadores.forEach(v -> v.validar(request));

        var cliente = clienteRepository.getReferenceById(request.idCliente());
        var barbeiro = escolherBarbeiro(request);

        if (barbeiro == null) {
            throw new ValidacaoException("Não existe barbeiro disponível nessa data!");
        }
        var agendamento = new Agendamento(null, barbeiro, cliente, request.data(), null);
        agendamentoRepository.save(agendamento);
        return new AgendamentoResponse(agendamento);
    }


    private Barbeiro escolherBarbeiro(AgendamentoRequest request) {

        if (request.idBarbeiro() != null) {
            return barbeiroRepository.getReferenceById(request.idBarbeiro());
        }
        if (request.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando barbeiro não for escolhido!");
        }
        return barbeiroRepository.escolherBarbeiroAleatorioLivreNaData(request.especialidade(), request.data());
    }

    public AgendamentoResponse verAgenda(Long id){
        var obj = agendamentoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id));
        return new AgendamentoResponse(obj);
    }

    public List<AgendamentoResponse> verTodaAgenda() {
        List<Agendamento> list = agendamentoRepository.findAll();
        List<AgendamentoResponse> listdados = list.stream().map(a -> new AgendamentoResponse(a)).collect(Collectors.toList());
        return listdados;
    }
}
