package com.diretoaocodigo.pedidos.domain.repository;

import com.diretoaocodigo.pedidos.domain.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}
