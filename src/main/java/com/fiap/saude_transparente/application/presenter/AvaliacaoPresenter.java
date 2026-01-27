package com.fiap.saude_transparente.application.presenter;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AvaliacaoPresenter implements Presenter<Map<String, Object>, NotaResponse>{

	@Override
	public NotaResponse presenter(Map<String, Object> estatistica) {

		return new NotaResponse(
				(Long) estatistica.get("medicoId"),
				(String) estatistica.get("medicoNome"),
				(String) estatistica.get("especialidade"),
				(Double) estatistica.get("mediaNotas"),
				(Integer) estatistica.get("totalAvaliacoes"),
				(Double) estatistica.get("notaMaxima"),
				(Double) estatistica.get("notaMinima")
		);
	}
}
