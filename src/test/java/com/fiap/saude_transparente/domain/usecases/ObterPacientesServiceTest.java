package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterPacientesServiceTest {

    @Mock
    private PacienteGateway pacienteGateway;

    @InjectMocks
    private ObterPacientesService obterPacientesService;

    @Test
    void deveRetornarListaDePacientes() {
        Paciente paciente1 = new Paciente();
        Paciente paciente2 = new Paciente();
        List<Paciente> pacientes = Arrays.asList(paciente1, paciente2);

        when(pacienteGateway.getAllPacientes(1, 10)).thenReturn(pacientes);

        List<Paciente> result = obterPacientesService.execute(1, 10);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(pacienteGateway).getAllPacientes(1, 10);
    }
}
