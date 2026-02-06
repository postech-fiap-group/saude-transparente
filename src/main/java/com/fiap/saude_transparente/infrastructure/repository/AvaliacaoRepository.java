package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.application.presenter.EstatisticaEspecialidadeDTO;
import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import com.fiap.saude_transparente.infrastructure.model.AvaliacaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Collections;
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
            m.id AS medicoId,
            m.nome AS medicoNome,
            m.especialidade AS especialidade,
            m.crm AS crm,
            COALESCE(AVG(a.nota), 0) AS mediaNotas,
            COALESCE(COUNT(a.id), 0) AS totalAvaliacoes,
            COALESCE(MIN(a.nota),0) AS notaMinima ,
            COALESCE(MAX(a.nota),0) AS notaMaxima
        FROM medico m
        LEFT JOIN consulta c ON m.id = c.medico_id
        LEFT JOIN avaliacao a ON c.id = a.consulta_id
        WHERE m.id = ?
        GROUP BY m.id, m.nome, m.especialidade
        """;
		return jdbcClient.sql(sql)
				.param(medicoId)
				.query(new ColumnMapRowMapper())
				.optional()
				.orElseGet(() -> buscarApenasMedico(medicoId));
	}

	@Override
	public List<EstatisticaEspecialidadeDTO> getEstatisticasAvaliacoesByEspecialidade() {
		String sql = """
        SELECT 
            m.especialidade AS especialidade,
            COALESCE(AVG(a.nota), 0) AS mediaNotas,
            COALESCE(COUNT(a.id), 0) AS totalAvaliacoes,
            COALESCE(MIN(a.nota),0) AS notaMinima ,
            COALESCE(MAX(a.nota),0) AS notaMaxima
        FROM medico m
        LEFT JOIN consulta c ON m.id = c.medico_id
        LEFT JOIN avaliacao a ON c.id = a.consulta_id
        GROUP BY m.especialidade
        """;

		return jdbcClient.sql(sql)
				.query((rs, rowNum) ->
						new EstatisticaEspecialidadeDTO(
							rs.getString("especialidade"),
							rs.getDouble("mediaNotas"),
							rs.getLong("totalAvaliacoes"),
							rs.getInt("notaMinima"),
							rs.getInt("notaMaxima")
					)
				)
				.list();
	}

	@Override
	public List<String> getAllComentariosByMedicoId(Long medicoId) {
		String sql = """
			SELECT DISTINCT a.comentario AS comentario
			FROM medico m
			JOIN consulta c ON m.id = c.medico_id
			JOIN avaliacao a ON c.id = a.consulta_id
			WHERE m.id = ?
			  AND a.comentario IS NOT NULL
			  AND TRIM(a.comentario) != ''
        """;
		return jdbcClient.sql(sql)
				.param(medicoId)
				.query((rs, rowNum) -> rs.getString("comentario"))
				.list();
	}

	private Map<String, Object> buscarApenasMedico(Long medicoId) {
		String sql = """
        SELECT 
            m.id AS medicoId,
            m.nome AS medicoNome,
            m.especialidade AS especialidade,
            m.crm AS crm,
            0 AS mediaNotas,
            0 AS totalAvaliacoes,
            0 AS notaMinima,
            0 AS notaMaxima
        FROM medico m
        WHERE m.id = ?
        """;

		return jdbcClient.sql(sql)
				.param(medicoId)
				.query(new ColumnMapRowMapper())
				.optional()
				.orElse(Collections.emptyMap());
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
