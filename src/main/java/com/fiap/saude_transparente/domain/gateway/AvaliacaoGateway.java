package com.fiap.saude_transparente.domain.gateway;


import com.fiap.saude_transparente.domain.entities.Avaliacao;

import java.math.BigDecimal;
import java.util.List;

public interface AvaliacaoGateway {

	Avaliacao getAvaliacaoById(Long id);
	List<Avaliacao> getAllAvaliacoesByMedicoId(Long medicoId, int size, int offset);
	BigDecimal getMediaNotaByMedicoId(Long medicoId);
	int getQuantidadeAvaliacoesByMedicoId(Long medicoId);
	Avaliacao criarAvaliacao(Avaliacao avaliacao);
	Avaliacao atualizarAvaliacao(Avaliacao avaliacao);
	void deletarAvaliacao(Long id);
}
