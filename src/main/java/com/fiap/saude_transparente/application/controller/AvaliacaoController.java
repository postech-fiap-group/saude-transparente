package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarAvaliacaoDTO;
import com.fiap.saude_transparente.domain.commands.CriarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.usecases.CriarAvaliacaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/avaliacaoes")
@AllArgsConstructor
@Tag(name = "Avaliacoes", description = "Avaliacoes de pacientes")
public class AvaliacaoController {

	private final CriarAvaliacaoService criarAvaliacaoService;
	private final Validator validator;

	@PostMapping
	public ResponseEntity<Void> avaliar(
			@RequestBody @Valid CriarAvaliacaoDTO avaliacaoDTO){

		var erros = this.validator.validateObject(avaliacaoDTO)
				.getAllErrors()
				.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toSet());

		this.criarAvaliacaoService.save(new CriarAvaliacaoCommand(avaliacaoDTO.consultaId()
				, avaliacaoDTO.nota(), avaliacaoDTO.comentario()));
		return ResponseEntity.status(200).build();
	}
}
