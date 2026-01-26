package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import com.fiap.saude_transparente.infrastructure.model.AvaliacaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AvaliacaoRepository implements AvaliacaoGateway {

	private final AvaliacaoJpaRepository avaliacaoJpaRepository;
	private final JdbcClient jdbcClient;

	@Override
	public List<Avaliacao> getAll(int size, int offset) {

		Pageable pageable = PageRequest.of(0, size).withPage(offset / size);
		Page<AvaliacaoEntity> pageResult = avaliacaoJpaRepository.findAll(pageable);

		return pageResult.getContent().stream().map(AvaliacaoEntity::toDomain).toList();
	}

	private Avaliacao convertToAvaliacao(AvaliacaoEntity entity) {
		Avaliacao avaliacao = new Avaliacao();
		avaliacao.setId(entity.getId());
		avaliacao.setConsultaId(entity.getConsultaId());
		avaliacao.setNota(entity.getNota());
		avaliacao.setComentario(entity.getComentario());
		return avaliacao;
	}

	@Override
	public Avaliacao getBydId(Long id) {
		return this.avaliacaoJpaRepository.findById(id)
				.map(AvaliacaoEntity::toDomain)
				.orElseThrow(() -> new RuntimeException("Avaliação não encontrada com ID: " + id));
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

		var newEntity = this.avaliacaoJpaRepository.save(new AvaliacaoEntity(avaliacao));

		return newEntity.getId();
	}

	@Override
	public Long alterarAvaliacao(Avaliacao avaliacao) {

		AvaliacaoEntity entidadeAtualizada = avaliacaoJpaRepository.findById(avaliacao.getId()).map(avaliacaoEntity -> {
			avaliacaoEntity.setConsultaId(avaliacao.getConsultaId());
			avaliacaoEntity.setNota(avaliacao.getNota());
			avaliacaoEntity.setComentario(avaliacao.getComentario());
			return avaliacaoJpaRepository.save(avaliacaoEntity);
		}).orElseThrow(() -> new RuntimeException("Avaliação não encontrada com ID: " + avaliacao.getId()));

		return entidadeAtualizada.getId();
	}

}
