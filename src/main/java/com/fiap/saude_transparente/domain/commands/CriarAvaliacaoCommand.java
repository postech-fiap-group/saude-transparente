package com.fiap.saude_transparente.domain.commands;

public record CriarAvaliacaoCommand(
		Long consultaId,

		Integer nota,

		String comentario) {
}
