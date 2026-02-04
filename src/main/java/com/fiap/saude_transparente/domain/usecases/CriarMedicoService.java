package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarMedicoCommand;
import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CriarMedicoService {

    private final MedicoGateway medicoRepository;

    public CriarMedicoService(MedicoGateway medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public void criar(CriarMedicoCommand medicoCommand) {
        Medico medico = Medico.criar(medicoCommand.nome(),
                medicoCommand.sobrenome(),
                medicoCommand.especialidade(),
                medicoCommand.crm(),
                medicoCommand.endereco(),
                medicoCommand.email(),
                medicoCommand.telefone(),
                medicoCommand.cpf(),
                medicoCommand.dataNascimento());
        this.medicoRepository.criarMedico(medico);
    }

}
