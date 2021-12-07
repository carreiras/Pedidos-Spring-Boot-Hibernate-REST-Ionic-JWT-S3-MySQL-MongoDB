package com.diretoaocodigo.pedidos.domain.repository;

import com.diretoaocodigo.pedidos.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
