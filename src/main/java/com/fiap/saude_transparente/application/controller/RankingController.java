package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.presenter.AvaliacaoPresenter;
import com.fiap.saude_transparente.application.presenter.ErrorRankingResponse;
import com.fiap.saude_transparente.application.presenter.EspecialidadePresenter;
import com.fiap.saude_transparente.domain.usecases.ObterComentariosPorMedicoId;
import com.fiap.saude_transparente.domain.usecases.ObterEstatisticaAvaliacaoPorEspecialidade;
import com.fiap.saude_transparente.domain.usecases.ObterEstatisticaAvaliacaoPorMedicoId;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/score")
@AllArgsConstructor
@Tag(name = "Score", description = "Score de Avaliacoes")
public class RankingController {
	private final AvaliacaoPresenter avaliacaoPresenter;
	private final EspecialidadePresenter especialidadePresenter;
	private final ObterEstatisticaAvaliacaoPorMedicoId obterEstatisticaAvaliacaoPorMedicoId;
	private final ObterEstatisticaAvaliacaoPorEspecialidade obterEstatisticaAvaliacaoPorEspecialidade;
	private final ObterComentariosPorMedicoId obterComentariosPorMedicoId;

	@ApiResponses(value = {	@ApiResponse(responseCode = "404", description = "Médico não foi encontrado.")})
	@GetMapping("/{medicoId}")
	public ResponseEntity<?> getAvaliacaoPorMedicoId(
			@PathVariable("medicoId") Long medicoId) {

		var estatistica = this.obterEstatisticaAvaliacaoPorMedicoId.execute(medicoId);
		Map<String, Object> details = new HashMap<>();
		if (isNull(estatistica) || estatistica.isEmpty()) {

			details.put("medicoId", medicoId);
			details.put("suggestion", "Verifique se o ID do médico está correto");

			ErrorRankingResponse error = new ErrorRankingResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
					HttpStatus.NOT_FOUND.getReasonPhrase(), "Médico não encontrado com o ID fornecido", details);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}

		return ResponseEntity.ok(avaliacaoPresenter.presenter(estatistica));
	}

	@ApiResponses(value = {	@ApiResponse(responseCode = "404", description = "Médico não foi encontrado.")})
	@GetMapping("/{medicoId}/comentarios")
	public ResponseEntity<?> getComentariosByMedicoId(
			@PathVariable("medicoId") Long medicoId) {
		var comentarios = this.obterComentariosPorMedicoId.execute(medicoId);

		Map<String, Object> details = new HashMap<>();
		if (isNull(comentarios) || comentarios.isEmpty()) {

			details.put("medicoId", medicoId);
			details.put("suggestion", "Verifique se o ID do médico está correto");

			ErrorRankingResponse error = new ErrorRankingResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
					HttpStatus.NOT_FOUND.getReasonPhrase(), "Médico não encontrado com o ID fornecido", details);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}

		return ResponseEntity.ok(comentarios);
	}

	@ApiResponses(value = {	@ApiResponse(responseCode = "404", description = "Especialidade não foi encontrado.")})
	@GetMapping("/especialidade")
	public ResponseEntity<?> getAllAvaliacaoByEspecialidade() {

		var estatistica = this.obterEstatisticaAvaliacaoPorEspecialidade.execute();
		Map<String, Object> details = new HashMap<>();
		if (isNull(estatistica) || estatistica.isEmpty()) {

			details.put("suggestion", "Não foram encontradas especialidades com avaliações");

			ErrorRankingResponse error = new ErrorRankingResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
					HttpStatus.NOT_FOUND.getReasonPhrase(), "Médico não encontrado com o ID fornecido",details);

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		}

		return ResponseEntity.ok(especialidadePresenter.presenter(estatistica));
	}

}
