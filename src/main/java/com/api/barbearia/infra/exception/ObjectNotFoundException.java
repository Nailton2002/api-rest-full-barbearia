package com.api.barbearia.infra.exception;

public class ObjectNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ObjectNotFoundException(Object id){
        super("Service não encontrado -> Id " + id);
    }
}
