package com.carreiras.java_spring_boot_pedidos.service.validation;

import com.carreiras.java_spring_boot_pedidos.domain.entity.Cliente;
import com.carreiras.java_spring_boot_pedidos.domain.enums.TipoCliente;
import com.carreiras.java_spring_boot_pedidos.rest.dto.ClienteNewDto;
import com.carreiras.java_spring_boot_pedidos.rest.exception.FieldMessage;
import com.carreiras.java_spring_boot_pedidos.service.validation.util.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.carreiras.java_spring_boot_pedidos.domain.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert constraintAnnotation) {
    }

    @Override
    public boolean isValid(ClienteNewDto clienteNewDto, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> list = new ArrayList<>();

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCpf(clienteNewDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CPF Inválido."));
        }

        if (clienteNewDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCnpj(clienteNewDto.getCpfCnpj())) {
            list.add(new FieldMessage("cpfCnpj", "CNPJ Inválido."));
        }

        Cliente cliente = clienteRepository.findByEmail(clienteNewDto.getEmail());
        if(cliente!= null) {
            list.add(new FieldMessage("email", "E-mail já existente."));
        }

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
