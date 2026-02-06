package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarPacienteDTO;
import com.fiap.saude_transparente.domain.commands.AlterarPacienteCommand;
import com.fiap.saude_transparente.domain.commands.CriarPacienteCommand;
import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.exceptions.InvalidFieldsException;
import com.fiap.saude_transparente.domain.usecases.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pacientes")
@AllArgsConstructor
@Tag(name = "Pacientes", description = "Pacientes do sistema")
public class PacienteController {

	private final CriarPacienteService criarPacienteService;
	private final AlterarPacienteService alterarPacienteService;
	private final ObterPacientesService obterPacientesService;
	private final ObterPacientePorIdService obterPacientePorIdService;
	private final DeletarPacienteService deletarPacienteService;
	private final Validator validator;

	@PostMapping
	public ResponseEntity<String> criar(@RequestBody @Valid CriarPacienteDTO dto) {

		validatorDto(dto);

		this.criarPacienteService.save(new CriarPacienteCommand(
				dto.nome(),
				dto.sobreNome(),
				dto.telefone(),
				dto.email(),
				dto.cpf(),
				dto.endereco(),
				dto.dataNascimento()
		));

		return ResponseEntity.status(HttpStatus.CREATED)
				.body("Paciente criado com sucesso! Bem-vindo ao nosso sistema de saúde!");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> alterar(
			@PathVariable("id") Long id,
			@RequestBody @Valid CriarPacienteDTO dto) {

		validatorDto(dto);

		var cmd = new AlterarPacienteCommand(
				id,
				dto.nome(),
				dto.sobreNome(),
				dto.telefone(),
				dto.email(),
				dto.cpf(),
				dto.endereco(),
				dto.dataNascimento()
		);

		this.alterarPacienteService.save(cmd);

		return ResponseEntity.status(HttpStatus.OK)
				.body("Paciente alterado com sucesso! Seus dados estão atualizados!");
	}

	@GetMapping
	public ResponseEntity<List<Paciente>> getAll(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		return ResponseEntity.status(HttpStatus.OK).body(this.obterPacientesService.execute(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Paciente> getById(
			@PathVariable("id") Long id) {

		return ResponseEntity.status(HttpStatus.OK).body(this.obterPacientePorIdService.execute(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(
			@PathVariable("id") Long id) {

		this.deletarPacienteService.execute(id);

		return ResponseEntity.status(HttpStatus.OK).body("Paciente deletado com sucesso!");
	}

	private void validatorDto(CriarPacienteDTO pacienteDTO) {
		var erros = this.validator.validateObject(pacienteDTO)
				.getAllErrors()
				.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
		if (!erros.isEmpty())
			throw new InvalidFieldsException(erros);
	}
}