package com.fiap.saude_transparente.application.presenter;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EspecialidadePresenter implements Presenter<List<EstatisticaEspecialidadeDTO>, List<EspecialidadeScoreResponse>> {

	@Override
	public List<EspecialidadeScoreResponse> presenter(List<EstatisticaEspecialidadeDTO> especialidades) {

		return especialidades.stream()
				.map(especialidade -> new EspecialidadeScoreResponse(especialidade.getEspecialidade(),
						new NotaResponse(
								BigDecimal.valueOf(especialidade.getMediaNotas()),
								especialidade.getTotalAvaliacoes().intValue(),
								especialidade.getNotaMinima() != null ? BigDecimal.valueOf(especialidade.getNotaMinima()) : BigDecimal.ZERO,
								especialidade.getNotaMaxima() != null ? BigDecimal.valueOf(especialidade.getNotaMaxima()) : BigDecimal.ZERO))).toList();

	}
}
