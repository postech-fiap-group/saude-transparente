package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.infrastructure.model.ConsultaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaJpaRepository extends JpaRepository<ConsultaEntity, Long> {
}
