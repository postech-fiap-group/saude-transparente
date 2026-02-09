package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.AlterarMedicoCommand;
import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AlterarMedicoServiceTest {

    @Mock
    private MedicoGateway medicoGateway;

    @InjectMocks
    private AlterarMedicoService alterarMedicoService;

    @Test
    void deveAlterarMedicoComSucesso() {
        AlterarMedicoCommand command = new AlterarMedicoCommand(
                1L, "Dr. House", "Gregory", "CARDIOLOGIA", "123456",
                "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));

        assertDoesNotThrow(() -> alterarMedicoService.update(command));

        verify(medicoGateway).atualizarMedico(any(Medico.class));
    }
}
