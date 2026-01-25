package com.fiap.saude_transparente.application.controller.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CriarAvaliacaoDTO(
	@NotNull(message = "Consulta ID não pode ser nulo")
	Long consultaId,

	@NotNull(message = "Nota não pode ser nula")
	@Min(value = 0, message = "Nota deve ser no mínimo 0")
	@Max(value = 5, message = "Nota deve ser no máximo 5")
	Integer nota,

	String comentario){}
