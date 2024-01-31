package com.api.barbearia.domain.service.agendamento;

import com.api.barbearia.domain.dto.agendamento.AgendamentoDadosCadastro;
import com.api.barbearia.domain.dto.agendamento.AgendamentoDadosCancelamento;
import com.api.barbearia.domain.dto.agendamento.AgendamentoDadosDetalhados;
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

    //USANDO O PADRÃO DE PROJETO -> Strategy
    @Autowired
    private List<ValidadorAgendamento> validadores;
    //AQUI É INJETADO UMA LISTA DE VALIDADORES...

    @Autowired
    private List<ValidadorCancelamentoDeAgendamento> validadoresCancelamento;

    public void cancelar(AgendamentoDadosCancelamento dados) {

        if (!agendamentoRepository.existsById(dados.idAgendamento())) {
            throw new ValidacaoException("Id do agendamento informado não existe!");
        }
        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = agendamentoRepository.getReferenceById(dados.idAgendamento());
        consulta.cancelar(dados.motivo());
    }

    public AgendamentoDadosDetalhados agendar(AgendamentoDadosCadastro dados) {

        if (!clienteRepository.existsById(dados.idCliente())) {
            throw new ValidacaoException("Id do cliente informado não existe!");
        }
        if (dados.idBarbeiro() != null && !barbeiroRepository.existsById(dados.idBarbeiro())) {
            throw new ValidacaoException("Id do barbeiro informado não existe!");
        }
        validadores.forEach(v -> v.validar(dados));

        var cliente = clienteRepository.getReferenceById(dados.idCliente());
        var barbeiro = escolherBarbeiro(dados);

        if (barbeiro == null) {
            throw new ValidacaoException("Não existe barbeiro disponível nessa data!");
        }

        var agendamento = new Agendamento(null, barbeiro, cliente, dados.data(), null);
        agendamentoRepository.save(agendamento);
        return new AgendamentoDadosDetalhados(agendamento);
    }


    private Barbeiro escolherBarbeiro(AgendamentoDadosCadastro dados) {

        if (dados.idBarbeiro() != null) {
            return barbeiroRepository.getReferenceById(dados.idBarbeiro());
        }
        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando barbeiro não for escolhido!");
        }
        return barbeiroRepository.escolherBarbeiroAleatorioLivreNaData(dados.especialidade(), dados.data());
    }

    public AgendamentoDadosDetalhados verAgenda(Long id){
        var obj = agendamentoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id));
        return new AgendamentoDadosDetalhados(obj);
    }

    public List<AgendamentoDadosDetalhados> verTodaAgenda() {
        List<Agendamento> list = agendamentoRepository.findAll();
        List<AgendamentoDadosDetalhados> listdados = list.stream().map(a -> new AgendamentoDadosDetalhados(a)).collect(Collectors.toList());
        return listdados;
    }
}
