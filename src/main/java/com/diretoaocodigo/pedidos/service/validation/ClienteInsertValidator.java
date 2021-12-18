package com.diretoaocodigo.pedidos.service.validation;

import com.diretoaocodigo.pedidos.rest.dto.ClienteNewDto;
import com.diretoaocodigo.pedidos.rest.exception.FieldMessage;

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

        // inclua os testes aqui, inserindo erros na lista

        for (FieldMessage e : list) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
