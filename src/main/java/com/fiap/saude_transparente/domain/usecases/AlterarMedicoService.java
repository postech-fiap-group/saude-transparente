package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.AlterarMedicoCommand;
import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AlterarMedicoService {

    private final MedicoGateway medicoGateway;

    public void update(AlterarMedicoCommand medicoCommand) {

        var medico = Medico.alterar(medicoCommand.id(),
                medicoCommand.nome(),
                medicoCommand.sobrenome(),
                medicoCommand.especialidade(),
                medicoCommand.crm(),
                medicoCommand.endereco(),
                medicoCommand.email(),
                medicoCommand.telefone(),
                medicoCommand.cpf(),
                medicoCommand.dataNascimento());
        this.medicoGateway.atualizarMedico(medico);

    }

}
