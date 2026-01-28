package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarConsultaCommand;
import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CriarConsultaService {

    private final ConsultaGateway consultaGateway;

    public void save(CriarConsultaCommand cmd) {
        var incluirConsulta = Consulta.criar(
                cmd.pacienteId(),
                cmd.medicoId(),
                cmd.dataConsulta(),
                cmd.motivo()
        );

        var id = this.consultaGateway.criarConsulta(incluirConsulta);

        if (id == null || id <= 0) {
            throw new RuntimeException("Erro ao salvar a consulta");
        }
    }

}
