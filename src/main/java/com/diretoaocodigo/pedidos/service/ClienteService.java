package com.diretoaocodigo.pedidos.service;

import com.diretoaocodigo.pedidos.domain.entity.Categoria;
import com.diretoaocodigo.pedidos.domain.entity.Cliente;
import com.diretoaocodigo.pedidos.domain.repository.ClienteRepository;
import com.diretoaocodigo.pedidos.exception.DataIntegratyException;
import com.diretoaocodigo.pedidos.exception.ObjectNotFoundException;
import com.diretoaocodigo.pedidos.rest.dto.CategoriaDto;
import com.diretoaocodigo.pedidos.rest.dto.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Cliente update(Cliente cliente) {
        this.find(cliente.getId());
        return clienteRepository.save(cliente);
    }

    public void delete(Integer id) {
        this.find(id);
        try {
            clienteRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyException("Não é possível excluir um Cliente que possui pedidos.");
        }
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDto clienteDto) {
        return new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);
    }
}
