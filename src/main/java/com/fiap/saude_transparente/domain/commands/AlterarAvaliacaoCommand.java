package com.fiap.saude_transparente.domain.commands;

public record AlterarAvaliacaoCommand(Long id,
		Long consultaId,
		Integer nota,
		String comentario) {
}
