package com.api.barbearia.infra.exception;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object id){

		super("Tarefa finalizada -> Id " + id);
	}
}
