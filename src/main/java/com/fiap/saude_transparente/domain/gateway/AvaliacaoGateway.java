package com.fiap.saude_transparente.domain.gateway;

import com.fiap.saude_transparente.application.presenter.EstatisticaEspecialidadeDTO;
import com.fiap.saude_transparente.domain.entities.Avaliacao;

import java.util.List;
import java.util.Map;

public interface AvaliacaoGateway {

	List<Avaliacao> getAll(int size, int offset);
	Avaliacao getBydId(Long id);
	Long criarAvaliacao(Avaliacao avaliacao);
	Long alterarAvaliacao(Avaliacao avaliacao);
	Map<String, Object> getEstatisticasAvaliacoesByMedicoId(Long medicoId);
	List<EstatisticaEspecialidadeDTO> getEstatisticasAvaliacoesByEspecialidade();
	List<String> getAllComentariosByMedicoId(Long medicoId);
}
