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

import java.util.List;
import java.util.Map;

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


	@Override
	public Avaliacao getBydId(Long id) {
		return this.avaliacaoJpaRepository.findById(id)
				.map(AvaliacaoEntity::toDomain)
				.orElseThrow(() -> new RuntimeException("Avaliação não encontrada com ID: " + id));
	}

	@Override
	public Map<String, Object> getEstatisticasAvaliacoesByMedicoId(Long medicoId) {

		String sql = """
        SELECT 
            m.id AS medico_id,
            m.nome AS medico_nome,
            m.especialidade,
            COALESCE(AVG(a.nota), 0) AS media_notas,
            COUNT(a.id) AS total_avaliacoes,
            MIN(a.nota) AS nota_minima,
            MAX(a.nota) AS nota_maxima
        FROM medico m
        LEFT JOIN consulta c ON m.id = c.medico_id
        LEFT JOIN avaliacao a ON c.id = a.consulta_id
        WHERE m.id = ?
        GROUP BY m.id, m.nome, m.especialidade
        """;
		try{
		return jdbcClient.sql(sql)
				.param(medicoId)
				.query(Map.class)
				.single();
		} catch (Exception e) {
			return buscarApenasMedico(medicoId);
		}
	}
	private Map<String, Object> buscarApenasMedico(Long medicoId) {
		String sql = """
        SELECT 
            m.id AS medico_id,
            m.nome AS medico_nome,
            m.especialidade,
            0 AS media_notas,
            0 AS total_avaliacoes,
            0 AS nota_minima,
            0 AS nota_maxima
        FROM medico m
        WHERE m.id = ?
        """;

		try {
			return jdbcClient.sql(sql)
					.param(medicoId)
					.query(Map.class)
					.list().getFirst();
		} catch (Exception e) {
			// Médico não encontrado
			return null;
		}
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
