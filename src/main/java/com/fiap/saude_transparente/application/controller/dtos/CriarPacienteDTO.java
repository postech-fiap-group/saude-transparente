package com.fiap.saude_transparente.application.controller.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record CriarPacienteDTO(
		@NotBlank(message = "O campo nome é obrigatório.")
		String nome,

		String sobreNome,

		@NotBlank(message = "O campo endereco é obrigatório.")
		String endereco,

		@Email(message = "O e-mail informado é inválido!")
		@NotBlank(message = "O campo e-mail é obrigatório.")
		String email,

		String telefone,

		@NotBlank(message = "O campo cpf é obrigatório.")
		String cpf,

		LocalDate dataNascimento

		) {
}
