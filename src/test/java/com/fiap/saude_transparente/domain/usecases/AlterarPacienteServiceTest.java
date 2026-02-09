package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.AlterarPacienteCommand;
import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.exceptions.PacienteNaoOuNaoPodeCriarException;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarPacienteServiceTest {

    @Mock
    private PacienteGateway pacienteGateway;

    @InjectMocks
    private AlterarPacienteService alterarPacienteService;

    @Test
    void deveAlterarPacienteComSucesso() {
        AlterarPacienteCommand command = new AlterarPacienteCommand(
                1L, "João", "Silva", "11999998888", "joao@email.com",
                "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        when(pacienteGateway.alterarPaciente(any(Paciente.class))).thenReturn(1L);

        assertDoesNotThrow(() -> alterarPacienteService.save(command));

        verify(pacienteGateway).alterarPaciente(any(Paciente.class));
    }

    @Test
    void deveLancarExcecaoQuandoFalhar() {
        AlterarPacienteCommand command = new AlterarPacienteCommand(
                1L, "João", "Silva", "11999998888", "joao@email.com",
                "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        when(pacienteGateway.alterarPaciente(any(Paciente.class))).thenReturn(null);

        assertThrows(PacienteNaoOuNaoPodeCriarException.class, () -> alterarPacienteService.save(command));
    }
}
