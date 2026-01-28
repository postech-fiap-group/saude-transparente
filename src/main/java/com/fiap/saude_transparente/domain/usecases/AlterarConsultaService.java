package com.fiap.saude_transparente.domain.usecases;


import com.fiap.saude_transparente.domain.commands.AlterarConsultaCommand;
import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AlterarConsultaService {

    private final ConsultaGateway consultaGateway;

    public void save(AlterarConsultaCommand cmd) {

        var id = this.consultaGateway.alterarConsulta(
                Consulta.alterar(cmd.id(),
                        cmd.pacienteId(),
                        cmd.medicoId(),
                        cmd.dataConsulta(),
                        cmd.motivo()));

        if (id == null || id <= 0) {
            throw new RuntimeException("Erro ao alterar a consulta");
        }
    }
}
