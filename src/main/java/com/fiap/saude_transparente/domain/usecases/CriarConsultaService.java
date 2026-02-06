package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarConsultaCommand;
import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.exceptions.MedicoNaoEncontradoException;
import com.fiap.saude_transparente.domain.exceptions.PacienteNaoEncontradoException;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CriarConsultaService {

    private final ConsultaGateway consultaGateway;
    private final PacienteGateway pacienteGateway;
    private final MedicoGateway medicoGateway;

    public void save(CriarConsultaCommand cmd) {

        if(!pacienteGateway.existePacienteById(cmd.pacienteId())) {
            throw new PacienteNaoEncontradoException("Paciente " + cmd.pacienteId() + " não existe.");
        }

        if(!medicoGateway.existeMedicoById(cmd.medicoId())) {
            throw new MedicoNaoEncontradoException("Médico " + cmd.medicoId() + " não existe.");
        }

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
