package com.carreiras.pedidos.rest.dto;

import com.carreiras.pedidos.domain.entity.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.carreiras.pedidos.service.validation.ClienteUpdate;

import java.io.Serializable;

@ClienteUpdate
public class ClienteDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório.")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres.")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório.")
    @Email(message = "E-mail inválido.")
    private String email;

    public ClienteDto() {
    }

    public ClienteDto(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
