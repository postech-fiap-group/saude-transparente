package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import com.fiap.saude_transparente.infrastructure.model.AvaliacaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AvaliacaoRepository implements AvaliacaoGateway {

	private final AvaliacaoJpaRepository avaliacaoJpaRepository;

	@Override
	public Avaliacao getAvaliacaoById(Long id) {
		return null;
	}

	@Override
	public List<Avaliacao> getAllAvaliacoesByMedicoId(Long medicoId, int size, int offset) {
		return List.of();
	}

	@Override
	public BigDecimal getMediaNotaByMedicoId(Long medicoId) {
		return null;
	}

	@Override
	public int getQuantidadeAvaliacoesByMedicoId(Long medicoId) {
		return 0;
	}

	@Override
	public Long criarAvaliacao(Avaliacao avaliacao) {

		var newEntity = this.avaliacaoJpaRepository.save( new AvaliacaoEntity(avaliacao));

		return newEntity.getId();
	}

	@Override
	public Avaliacao atualizarAvaliacao(Avaliacao avaliacao) {
		return null;
	}

	@Override
	public void deletarAvaliacao(Long id) {

	}
}
