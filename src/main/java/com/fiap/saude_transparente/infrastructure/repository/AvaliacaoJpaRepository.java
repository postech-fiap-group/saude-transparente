package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.infrastructure.model.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AvaliacaoJpaRepository  extends JpaRepository<AvaliacaoEntity, Long> {

	@Query("SELECT avaliacao FROM AvaliacaoEntity avaliacao " +
			"JOIN ConsultaEntity consulta ON avaliacao.consultaId = consulta.id " +
			"WHERE consulta.medicoId = :medicoId " +
			"ORDER BY avaliacao.id DESC")
	List<AvaliacaoEntity> findByMedicoId(@Param("medicoId") Long medicoId);
}
