package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterAvaliacaoPorId {

	private final AvaliacaoGateway avaliacaoGateway;

	public Avaliacao execute(Long id){

		return this.avaliacaoGateway.getBydId(id);
	}
}
