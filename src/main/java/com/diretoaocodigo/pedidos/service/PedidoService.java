package com.diretoaocodigo.pedidos.service;

import com.diretoaocodigo.pedidos.domain.entity.Categoria;
import com.diretoaocodigo.pedidos.domain.entity.Pedido;
import com.diretoaocodigo.pedidos.domain.repository.PedidoRepository;
import com.diretoaocodigo.pedidos.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }
}
