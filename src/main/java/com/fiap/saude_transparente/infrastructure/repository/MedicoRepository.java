package com.fiap.saude_transparente.infrastructure.repository;

import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.enums.Especialidades;
import com.fiap.saude_transparente.domain.exceptions.MedicoNaoEncontradoException;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import com.fiap.saude_transparente.infrastructure.model.MedicoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class MedicoRepository implements MedicoGateway {

    private final MedicoJpaRepository medicoJpaRepository;
    private final JdbcClient jdbcClient;

    @Override
    public List<Medico> getAllMedicos(int size, int offset) {

        Pageable pageable = PageRequest.of(0, size).withPage(offset / size);
        Page<MedicoEntity> pageResult = medicoJpaRepository.findAll(pageable);

        return pageResult.getContent().stream().map(MedicoEntity::toDomain).toList();
    }


    @Override
    public Medico getMedicoById(Long id) throws MedicoNaoEncontradoException {
        return this.medicoJpaRepository.findById(id)
                .map(MedicoEntity::toDomain)
                .orElseThrow(() -> new MedicoNaoEncontradoException("Médico não encontrada com ID: " + id));
    }

    @Override
    public Medico criarMedico(Medico medico) {
        MedicoEntity medicoEntity = medicoJpaRepository.save(new MedicoEntity(medico));
        return medicoEntity.toDomain();
    }

    @Override
    public boolean existeMedicoById(Long id) {
        return this.medicoJpaRepository.existsById(id);
    }

    @Override
    public void atualizarMedico(Medico medico) {

        medicoJpaRepository.findById(medico.getId()).map(medicoEntity -> {
            medicoEntity.setNome(medico.getNome());
            medicoEntity.setSobrenome(medico.getSobrenome());
            medicoEntity.setEspecialidade(Especialidades.from(medico.getEspecialidade()));
            medicoEntity.setCrm(medico.getCrm());
            medicoEntity.setEndereco(medico.getEndereco());
            medicoEntity.setEmail(medico.getEmail());
            medicoEntity.setTelefone(medico.getTelefone());
            medicoEntity.setCpf(medico.getCpf());
            medicoEntity.setDataNascimento(medico.getDataNascimento());
            return medicoJpaRepository.save(medicoEntity);
        }).orElseThrow(() -> new MedicoNaoEncontradoException("Medico não encontrada com ID: " + medico.getId()));
    }

    @Override
    public void deletarMedico(Long id) {

        if (!medicoJpaRepository.existsById(id))  {
        throw new MedicoNaoEncontradoException("Medico não encontrada com ID: " + id);
        }
        medicoJpaRepository.deleteById(id);

    }

}
