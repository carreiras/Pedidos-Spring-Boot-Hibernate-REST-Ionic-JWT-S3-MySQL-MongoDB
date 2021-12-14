package com.diretoaocodigo.pedidos.service;

import com.diretoaocodigo.pedidos.domain.entity.Categoria;
import com.diretoaocodigo.pedidos.domain.repository.CategoriaRepository;
import com.diretoaocodigo.pedidos.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id +
                ", Tipo: " + Categoria.class.getName()));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        this.find(categoria.getId());
        return categoriaRepository.save(categoria);
    }
}
