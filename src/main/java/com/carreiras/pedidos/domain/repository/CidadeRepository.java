package com.carreiras.pedidos.domain.repository;

import com.carreiras.pedidos.domain.entity.Cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}
