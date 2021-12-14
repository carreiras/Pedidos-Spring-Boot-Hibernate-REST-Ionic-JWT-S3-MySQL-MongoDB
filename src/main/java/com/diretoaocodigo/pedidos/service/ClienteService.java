package com.diretoaocodigo.pedidos.service;

import com.diretoaocodigo.pedidos.domain.entity.Cliente;
import com.diretoaocodigo.pedidos.domain.repository.ClienteRepository;
import com.diretoaocodigo.pedidos.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente find(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Cliente.class.getName()));
    }
}
