package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.infrastructure.model.AvaliacaoEntity;
import com.fiap.saude_transparente.infrastructure.model.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicoJpaRepository extends JpaRepository<MedicoEntity, Long> {

/*	@Query("SELECT medico FROM MedicoEntity medico " +
			"JOIN ConsultaEntity consulta ON avaliacao.consultaId = consulta.id " +
			"WHERE consulta.medicoId = :medicoId " +
			"ORDER BY avaliacao.id DESC")
	List<AvaliacaoEntity> findByMedicoId(@Param("medicoId") Long medicoId);*/
}
