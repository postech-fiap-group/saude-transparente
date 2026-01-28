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

    public void update(AlterarMedicoCommand cmd){

        var id = this.medicoGateway.atualizarMedico(Medico.alterar(
                cmd.id(),
                cmd.consultaId(),
                cmd.nota(),
                cmd.comentario()));

        if (id == null || id <= 0) {
            throw new RuntimeException("Erro ao alterar médico");
        }
    }

}
