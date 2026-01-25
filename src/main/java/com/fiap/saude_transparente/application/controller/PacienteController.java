package com.fiap.saude_transparente.application.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
@AllArgsConstructor
@Tag(name = "Pacientes", description = "Cadastro de pacientes")
public class PacienteController {

	@Operation(summary = "Listar todos os pacientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de pacientes retornada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do servidor")
	})
	@GetMapping
	public ResponseEntity<List<String>> listarPacientes() {
		// Exemplo - implementar com repositório
		return ResponseEntity.ok(List.of("Paciente 1", "Paciente 2"));
	}
}
