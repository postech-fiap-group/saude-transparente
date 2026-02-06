package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarAvaliacaoDTO;
import com.fiap.saude_transparente.application.controller.dtos.CriarMedicoDTO;
import com.fiap.saude_transparente.domain.commands.AlterarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.commands.CriarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.exceptions.InvalidFieldsException;
import com.fiap.saude_transparente.domain.usecases.AlterarAvaliacaoService;
import com.fiap.saude_transparente.domain.usecases.CriarAvaliacaoService;
import com.fiap.saude_transparente.domain.usecases.ObterAvaliacaoPorId;
import com.fiap.saude_transparente.domain.usecases.ObterTodasAvaliacoesService;
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
@RequestMapping("/avaliacaoes")
@AllArgsConstructor
@Tag(name = "Avaliacoes", description = "Avaliacoes de pacientes")
public class AvaliacaoController {

	private final CriarAvaliacaoService criarAvaliacaoService;
	private final AlterarAvaliacaoService alterarAvaliacaoService;
	private final ObterTodasAvaliacoesService obterTodasAvaliacoesService;
	private final ObterAvaliacaoPorId obterAvaliacaoPorId;
	private final Validator validator;

	@PostMapping
	public ResponseEntity<Void> avaliar(
			@RequestBody @Valid CriarAvaliacaoDTO dto){
		
		validatorDto(dto);

		this.criarAvaliacaoService.save(new CriarAvaliacaoCommand(
				dto.consultaId(),
				dto.nota(), 
				dto.comentario())
		);
		
		return ResponseEntity.status(200).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> alterar(
			@PathVariable("id") Long id,
			@RequestBody CriarAvaliacaoDTO dto) {

		validatorDto(dto);

		var cmd = new AlterarAvaliacaoCommand(
				id,
				dto.consultaId(),
				dto.nota(),
				dto.comentario()
		);

		this.alterarAvaliacaoService.save(cmd);

		var status = HttpStatus.NO_CONTENT;
		return ResponseEntity.status(status.value()).build();
	}

	@GetMapping
	public ResponseEntity<List<Avaliacao>> getAllAvaliacao(
			@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "size", defaultValue = "10") int size) {

		return ResponseEntity.ok(this.obterTodasAvaliacoesService.execute(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Avaliacao> getById(
			@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.obterAvaliacaoPorId.execute(id));
	}

	private void validatorDto(CriarAvaliacaoDTO avaliacaoDTO) {
		var erros = this.validator.validateObject(avaliacaoDTO)
				.getAllErrors()
				.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());
		if (!erros.isEmpty())
			throw new InvalidFieldsException(erros);
	}

}