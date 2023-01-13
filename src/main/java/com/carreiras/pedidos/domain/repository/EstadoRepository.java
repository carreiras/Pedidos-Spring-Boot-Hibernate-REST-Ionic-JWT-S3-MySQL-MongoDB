package com.carreiras.pedidos.domain.repository;

import com.carreiras.pedidos.domain.entity.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
