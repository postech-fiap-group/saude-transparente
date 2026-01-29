package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarMedicoCommand;
import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeletarMedicoService {

    private final MedicoGateway medicoRepository;

    public DeletarMedicoService(MedicoGateway medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public void deletar(Long id) {
        this.medicoRepository.deletarMedico(id);
    }

}
