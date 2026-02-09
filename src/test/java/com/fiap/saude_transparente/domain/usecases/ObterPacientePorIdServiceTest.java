package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterPacientePorIdServiceTest {

    @Mock
    private PacienteGateway pacienteGateway;

    @InjectMocks
    private ObterPacientePorIdService obterPacientePorIdService;

    @Test
    void deveRetornarPacientePorId() {
        Long id = 1L;
        Paciente paciente = new Paciente();
        paciente.setId(id);

        when(pacienteGateway.getPacienteById(id)).thenReturn(paciente);

        Paciente result = obterPacientePorIdService.execute(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(pacienteGateway).getPacienteById(id);
    }
}
