package com.fiap.saude_transparente.domain.exceptions;

public class MedicoNaoEncontradoException extends RuntimeException {
	public MedicoNaoEncontradoException(String message) {
		super(message);
	}
}
