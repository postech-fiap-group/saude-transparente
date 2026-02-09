package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarMedicoCommand;
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
class CriarMedicoServiceTest {

    @Mock
    private MedicoGateway medicoGateway;

    @InjectMocks
    private CriarMedicoService criarMedicoService;

    @Test
    void deveCriarMedicoComSucesso() {
        CriarMedicoCommand command = new CriarMedicoCommand(
                "Dr. House", "Gregory", "Diagnosta", "123456",
                "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));

        assertDoesNotThrow(() -> criarMedicoService.criar(command));

        verify(medicoGateway).criarMedico(any(Medico.class));
    }
}
