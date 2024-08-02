package com.carreiras.java_spring_boot_pedidos.service;

import com.carreiras.java_spring_boot_pedidos.domain.entity.Categoria;
import com.carreiras.java_spring_boot_pedidos.domain.repository.CategoriaRepository;
import com.carreiras.java_spring_boot_pedidos.exception.DataIntegratyException;
import com.carreiras.java_spring_boot_pedidos.exception.ObjectNotFoundException;
import com.carreiras.java_spring_boot_pedidos.rest.dto.CategoriaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Categoria categoriaToUpdate = find(categoria.getId());
        updateData(categoriaToUpdate, categoria);
        return categoriaRepository.save(categoriaToUpdate);
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegratyException("Não é possível excluir uma Categoria que possui produtos.");
        }
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
    }

    public Categoria fromDTO(CategoriaDto categoriaDTO) {
        return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
    }

    private void updateData(Categoria categoriaToUpdate, Categoria categoria) {
        categoriaToUpdate.setNome(categoria.getNome());
    }
}
