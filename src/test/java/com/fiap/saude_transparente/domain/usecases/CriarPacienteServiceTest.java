package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarPacienteCommand;
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
class CriarPacienteServiceTest {

    @Mock
    private PacienteGateway pacienteGateway;

    @InjectMocks
    private CriarPacienteService criarPacienteService;

    @Test
    void deveCriarPacienteComSucesso() {
        CriarPacienteCommand command = new CriarPacienteCommand(
                "João", "Silva", "11999998888", "joao@email.com",
                "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        when(pacienteGateway.criarPaciente(any(Paciente.class))).thenReturn(1L);

        assertDoesNotThrow(() -> criarPacienteService.save(command));

        verify(pacienteGateway).criarPaciente(any(Paciente.class));
    }

    @Test
    void deveLancarExcecaoQuandoFalhar() {
        CriarPacienteCommand command = new CriarPacienteCommand(
                "João", "Silva", "11999998888", "joao@email.com",
                "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        when(pacienteGateway.criarPaciente(any(Paciente.class))).thenReturn(null);

        assertThrows(PacienteNaoOuNaoPodeCriarException.class, () -> criarPacienteService.save(command));
    }

    @Test
    void deveLancarExcecaoQuandoIdForZero() {
        CriarPacienteCommand command = new CriarPacienteCommand(
                "João", "Silva", "11999998888", "joao@email.com",
                "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        when(pacienteGateway.criarPaciente(any(Paciente.class))).thenReturn(0L);

        assertThrows(PacienteNaoOuNaoPodeCriarException.class, () -> criarPacienteService.save(command));
    }
}
