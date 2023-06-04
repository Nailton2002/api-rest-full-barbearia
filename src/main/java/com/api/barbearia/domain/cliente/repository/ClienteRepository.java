package com.api.barbearia.domain.cliente.repository;

import com.api.barbearia.domain.cliente.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Page<Cliente> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "select c from Cliente c where c.nome like %?1%")
    List<Cliente> findByNome(String nome);

    @Query("""
            select c.ativo
            from Cliente c
            where
            c.id = :id
            """)
    Boolean findAtivoById(Long id);
}
