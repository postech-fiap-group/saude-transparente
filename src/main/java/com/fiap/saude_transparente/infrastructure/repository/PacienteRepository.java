package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.exceptions.PacienteNaoEncontradoException;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import com.fiap.saude_transparente.infrastructure.model.PacienteEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class PacienteRepository implements PacienteGateway {

    private final PacienteJpaRepository pacienteJpaRepository;

    @Override
    public List<Paciente> getAllPacientes(int page, int size) {
        int pageIndex = page > 0 ? page - 1 : 0;
        Pageable pageable = PageRequest.of(pageIndex, size);
        Page<PacienteEntity> pageResult = pacienteJpaRepository.findAll(pageable);

        return pageResult.getContent()
                .stream()
                .map(PacienteEntity::toDomain)
                .toList();
    }


    @Override
    public Paciente getPacienteById(Long id) {
        return this.pacienteJpaRepository.findById(id)
                .map(PacienteEntity::toDomain)
                .orElseThrow(() -> new PacienteNaoEncontradoException("Paciente não encontrado com ID: " + id));
    }

    @Override
    public Long criarPaciente(Paciente paciente) {
        var newEntity = this.pacienteJpaRepository.save(new PacienteEntity(paciente));
        return newEntity.getId();
    }

    @Override
    public boolean existePacienteById(Long id) {
        return this.pacienteJpaRepository.existsById(id);
    }

    @Override
    public Long alterarPaciente(Paciente paciente) {
        PacienteEntity entidadeAtualizada = pacienteJpaRepository.findById(paciente.getId())
                .map(pacienteEntity -> {
                    pacienteEntity.setNome(paciente.getNome());
                    pacienteEntity.setSobrenome(paciente.getSobrenome());
                    pacienteEntity.setTelefone(paciente.getTelefone());
                    pacienteEntity.setEmail(paciente.getEmail());
                    pacienteEntity.setCpf(paciente.getCpf());
                    pacienteEntity.setEndereco(paciente.getEndereco());
                    pacienteEntity.setDataNascimento(paciente.getDataNascimento());
                    return pacienteJpaRepository.save(pacienteEntity);
                })
                .orElseThrow(() ->
                        new PacienteNaoEncontradoException("Paciente não encontrado com ID: " + paciente.getId())
                );

        return entidadeAtualizada.getId();
    }

    @Override
    public void deletarPaciente(Long id) {
        if (!this.pacienteJpaRepository.existsById(id)) {
            throw new PacienteNaoEncontradoException("Paciente não encontrado com ID: " + id);
        }
        this.pacienteJpaRepository.deleteById(id);
    }
}