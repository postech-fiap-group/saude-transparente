package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObterPacientesService {

    private final PacienteGateway pacienteGateway;

    public List<Paciente> execute(int page, int size) {
        return this.pacienteGateway.getAllPacientes(page, size);
    }
}
