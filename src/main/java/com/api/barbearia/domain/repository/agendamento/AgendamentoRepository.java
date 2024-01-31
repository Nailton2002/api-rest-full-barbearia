package com.api.barbearia.domain.repository.agendamento;

import com.api.barbearia.domain.entity.agendamento.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    boolean existsByClienteIdAndDataBetween(Long idCliente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByBarbeiroIdAndData(Long idBarbeiro, LocalDateTime data);
}
