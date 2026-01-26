package com.fiap.saude_transparente.domain.gateway;

import com.fiap.saude_transparente.domain.entities.Avaliacao;

import java.math.BigDecimal;
import java.util.List;

public interface AvaliacaoGateway {

	List<Avaliacao> getAll(int size, int offset);
	Avaliacao getBydId(Long id);
	List<Avaliacao> getAllAvaliacoesByMedicoId(Long medicoId, int size, int offset);
	BigDecimal getMediaNotaByMedicoId(Long medicoId);
	int getQuantidadeAvaliacoesByMedicoId(Long medicoId);
	Long criarAvaliacao(Avaliacao avaliacao);
	Long alterarAvaliacao(Avaliacao avaliacao);
}
