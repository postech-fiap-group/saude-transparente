package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterPacientePorIdService {

    private final PacienteGateway pacienteGateway;

    public Paciente execute(Long id) {
        return this.pacienteGateway.getPacienteById(id);
    }
}
