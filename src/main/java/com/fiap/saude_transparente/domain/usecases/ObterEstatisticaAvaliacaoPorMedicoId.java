package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ObterEstatisticaAvaliacaoPorMedicoId {

	private final AvaliacaoGateway avaliacaoGateway;

	public Map<String, Object> execute(Long medicoId){
		Map<String, Object> estatisticas = this.avaliacaoGateway
				.getEstatisticasAvaliacoesByMedicoId(medicoId);

		if (estatisticas == null || estatisticas.isEmpty()) {
			throw new RuntimeException("Médico não encontrado ou não possui avaliações");
		}

		return estatisticas;
	}
}
