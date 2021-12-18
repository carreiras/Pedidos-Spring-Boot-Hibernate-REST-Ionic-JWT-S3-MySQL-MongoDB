package com.diretoaocodigo.pedidos.rest.controller;

import com.diretoaocodigo.pedidos.domain.entity.Categoria;
import com.diretoaocodigo.pedidos.domain.entity.Pedido;
import com.diretoaocodigo.pedidos.domain.entity.Produto;
import com.diretoaocodigo.pedidos.rest.dto.CategoriaDto;
import com.diretoaocodigo.pedidos.rest.dto.ProdutoDto;
import com.diretoaocodigo.pedidos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id) {
        Produto produto = produtoService.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDto>> findPage(
            @RequestParam(value = "nome", defaultValue = "") Integer nome,
            @RequestParam(value = "categorias", defaultValue = "") Integer categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Produto> produtoPage = produtoService.search(page, linesPerPage, orderBy, direction);
        Page<CategoriaDto> categoriaPageDto = produtoPage.map(categoria -> new CategoriaDto(categoria));
        return ResponseEntity.ok().body(categoriaPageDto);
    }
}
