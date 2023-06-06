package com.api.barbearia.domain.barbeiro.repository;

import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    boolean existsByEmail(String email);
    boolean existsByTelefone(String fone);

    Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "select b from Barbeiro b where b.nome like %?1%")
    List<Barbeiro> findByNome(String nome);

    @Query("""
            select b.ativo from Barbeiro b
            where
            b.id = :id
            """)
    Boolean findAtivoById(Long id);

    @Query("""
            select b from Barbeiro b
            where
            b.ativo = true
            and
            b.especialidade = :especialidade
            and
            b.id not in(
                select a.barbeiro.id from Agendamento a
                where
                a.data = :data
                and
                a.motivoCancelamento is null
            )
            order by rand()
            limit 1
        """)
    Barbeiro escolherBarbeiroAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
