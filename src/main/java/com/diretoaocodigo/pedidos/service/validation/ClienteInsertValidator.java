package com.diretoaocodigo.pedidos.service.validation;

import com.diretoaocodigo.pedidos.domain.enums.TipoCliente;
import com.diretoaocodigo.pedidos.rest.dto.ClienteNewDto;
import com.diretoaocodigo.pedidos.rest.exception.FieldMessage;
import com.diretoaocodigo.pedidos.service.validation.util.BR;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDto> {

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

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
