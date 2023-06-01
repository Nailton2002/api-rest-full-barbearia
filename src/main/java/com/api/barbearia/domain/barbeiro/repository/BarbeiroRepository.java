package com.api.barbearia.domain.barbeiro.repository;

import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "select b from Barbeiro b where b.nome like %?1%")
    List<Barbeiro> findByNome(String nome);
}
