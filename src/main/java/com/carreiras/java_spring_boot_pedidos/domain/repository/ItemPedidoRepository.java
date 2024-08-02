package com.carreiras.java_spring_boot_pedidos.domain.repository;

import com.carreiras.java_spring_boot_pedidos.domain.entity.ItemPedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}
