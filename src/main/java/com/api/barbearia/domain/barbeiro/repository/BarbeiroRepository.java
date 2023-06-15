package com.api.barbearia.domain.barbeiro.repository;

import com.api.barbearia.domain.barbeiro.entity.Barbeiro;
import com.api.barbearia.domain.barbeiro.enums.Especialidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    boolean existsByEmail(String email);

    boolean existsByTelefone(String fone);

    Page<Barbeiro> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "select b from Barbeiro b where b.nome like %?1%")
    List<Barbeiro> findByNome(String nome);

//    @Query("Select b from Barbeiro where b.nome = : nome")
//    List<Barbeiro> findByNomeParam(@Param("nome") String nome);
//
//    @Modifying
//    @Transactional
//    @Query("delete from Barbeiro b where b.nome = ?1")
//    void deleteByNome(String nome);
//
//    @Modifying
//    @Transactional
//    @Query("update Barbeiro b set b.nome = ?1 where b.email = ?2")
//    void updateByNome(String nome, String email);

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
