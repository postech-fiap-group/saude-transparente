package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletarPacienteServiceTest {

    @Mock
    private PacienteGateway pacienteGateway;

    @InjectMocks
    private DeletarPacienteService deletarPacienteService;

    @Test
    void deveDeletarPacienteComSucesso() {
        Long id = 1L;

        assertDoesNotThrow(() -> deletarPacienteService.execute(id));

        verify(pacienteGateway).deletarPaciente(id);
    }
}
