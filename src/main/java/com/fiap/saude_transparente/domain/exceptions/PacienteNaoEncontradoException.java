package com.fiap.saude_transparente.domain.exceptions;

public class PacienteNaoEncontradoException extends RuntimeException {
	public PacienteNaoEncontradoException(String message) {
		super(message);
	}
}
