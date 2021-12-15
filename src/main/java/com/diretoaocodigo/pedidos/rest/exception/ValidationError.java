package com.diretoaocodigo.pedidos.rest.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

    private static final long serialVersionUID = 1L;

    private List<FieldMessage> fieldMessageList = new ArrayList<>();

    public ValidationError(Integer status, String msg, Long timeStamp) {
        super(status, msg, timeStamp);
    }

    public List<FieldMessage> getFieldMessageList() {
        return fieldMessageList;
    }

    public void addError(String fieldName, String message) {
        this.fieldMessageList.add(new FieldMessage(fieldName, message));
    }
}
