package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.infrastructure.model.AvaliacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AvaliacaoJpaRepository  extends JpaRepository<AvaliacaoEntity, Long> {
}
