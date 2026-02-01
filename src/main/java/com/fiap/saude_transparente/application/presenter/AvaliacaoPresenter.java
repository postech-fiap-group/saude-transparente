package com.fiap.saude_transparente.application.presenter;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class AvaliacaoPresenter implements Presenter<Map<String, Object>, ScoreResponse>{

	@Override
	public ScoreResponse presenter(Map<String, Object> estatistica) {
		var total = (Number) estatistica.get("totalAvaliacoes");
		var notaMinima = (Long) estatistica.get("notaMinima");
		var notaMaxima = (Long) estatistica.get("notaMaxima");

		return new ScoreResponse(
				(Long) estatistica.get("medicoId"),
				(String) estatistica.get("medicoNome"),
				(String) estatistica.get("especialidade"),
				(String) estatistica.get("crm"),
				new NotaResponse((BigDecimal) estatistica.get("mediaNotas"),
						total.intValue(),
						notaMinima != null ? BigDecimal.valueOf(notaMinima) : BigDecimal.ZERO,
						notaMaxima != null ? BigDecimal.valueOf(notaMaxima) : BigDecimal.ZERO));
	}
}
