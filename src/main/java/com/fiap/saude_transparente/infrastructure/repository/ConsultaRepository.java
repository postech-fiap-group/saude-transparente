package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import com.fiap.saude_transparente.infrastructure.model.ConsultaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ConsultaRepository implements ConsultaGateway {

    private final ConsultaJpaRepository consultaJpaRepository;

    @Override
    public List<Consulta> getAllConsultas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ConsultaEntity> pageResult = consultaJpaRepository.findAll(pageable);

        return pageResult.getContent().stream()
                .map(ConsultaEntity::toDomain)
                .toList();
    }

    @Override
    public Consulta getConsultaById(Long id) {
        return this.consultaJpaRepository.findById(id)
                .map(ConsultaEntity::toDomain)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + id));
    }

    @Override
    public Long criarConsulta(Consulta consulta) {
        var newEntity = this.consultaJpaRepository.save(new ConsultaEntity(consulta));
        return newEntity.getId();
    }

    @Override
    public Long alterarConsulta(Consulta consulta) {
        ConsultaEntity entidadeAtualizada = consultaJpaRepository.findById(consulta.getId())
                .map(consultaEntity -> {
                    consultaEntity.setPacienteId(consulta.getPacienteId());
                    consultaEntity.setMedicoId(consulta.getMedicoId());
                    consultaEntity.setDataConsulta(consulta.getDataConsulta());
                    consultaEntity.setMotivo(consulta.getMotivo());
                    return consultaJpaRepository.save(consultaEntity);
                }).orElseThrow(() -> new RuntimeException("Consulta não encontrada com ID: " + consulta.getId()));

        return entidadeAtualizada.getId();
    }

    @Override
    public void deletarConsulta(Long id) {
        if (!this.consultaJpaRepository.existsById(id)) {
            throw new RuntimeException("Consulta não encontrada com ID: " + id);
        }
        this.consultaJpaRepository.deleteById(id);
    }
}
