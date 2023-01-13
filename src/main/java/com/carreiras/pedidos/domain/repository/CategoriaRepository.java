package com.carreiras.pedidos.domain.repository;

import com.carreiras.pedidos.domain.entity.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
