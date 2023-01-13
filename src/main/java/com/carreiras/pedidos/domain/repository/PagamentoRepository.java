package com.carreiras.pedidos.domain.repository;

import com.carreiras.pedidos.domain.entity.Pagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
