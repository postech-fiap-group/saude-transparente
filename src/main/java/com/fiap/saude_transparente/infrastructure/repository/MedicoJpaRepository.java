package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.infrastructure.model.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoJpaRepository extends JpaRepository<MedicoEntity, Long> {
    boolean existsById(Long id);

/*	@Query("SELECT medico FROM MedicoEntity medico " +
			"JOIN ConsultaEntity consulta ON avaliacao.consultaId = consulta.id " +
			"WHERE consulta.medicoId = :medicoId " +
			"ORDER BY avaliacao.id DESC")
	List<AvaliacaoEntity> findByMedicoId(@Param("medicoId") Long medicoId);*/
}
