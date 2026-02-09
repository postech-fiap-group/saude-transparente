package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarConsultaCommand;
import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.exceptions.MedicoNaoEncontradoException;
import com.fiap.saude_transparente.domain.exceptions.PacienteNaoEncontradoException;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CriarConsultaServiceTest {

    @Mock
    private ConsultaGateway consultaGateway;
    @Mock
    private PacienteGateway pacienteGateway;
    @Mock
    private MedicoGateway medicoGateway;

    @InjectMocks
    private CriarConsultaService criarConsultaService;

    @Test
    void deveCriarConsultaComSucesso() {
        CriarConsultaCommand command = new CriarConsultaCommand(
                1L, 2L, LocalDateTime.now().plusDays(1), "Checkup");

        when(pacienteGateway.existePacienteById(1L)).thenReturn(true);
        when(medicoGateway.existeMedicoById(2L)).thenReturn(true);
        when(consultaGateway.criarConsulta(any(Consulta.class))).thenReturn(1L);

        assertDoesNotThrow(() -> criarConsultaService.save(command));

        verify(consultaGateway).criarConsulta(any(Consulta.class));
    }

    @Test
    void deveLancarExcecaoQuandoPacienteNaoExiste() {
        CriarConsultaCommand command = new CriarConsultaCommand(
                1L, 2L, LocalDateTime.now().plusDays(1), "Checkup");

        when(pacienteGateway.existePacienteById(1L)).thenReturn(false);

        assertThrows(PacienteNaoEncontradoException.class, () -> criarConsultaService.save(command));
    }

    @Test
    void deveLancarExcecaoQuandoMedicoNaoExiste() {
        CriarConsultaCommand command = new CriarConsultaCommand(
                1L, 2L, LocalDateTime.now().plusDays(1), "Checkup");

        when(pacienteGateway.existePacienteById(1L)).thenReturn(true);
        when(medicoGateway.existeMedicoById(2L)).thenReturn(false);

        assertThrows(MedicoNaoEncontradoException.class, () -> criarConsultaService.save(command));
    }

    @Test
    void deveLancarExcecaoQuandoFalharAoSalvar() {
        CriarConsultaCommand command = new CriarConsultaCommand(
                1L, 2L, LocalDateTime.now().plusDays(1), "Checkup");

        when(pacienteGateway.existePacienteById(1L)).thenReturn(true);
        when(medicoGateway.existeMedicoById(2L)).thenReturn(true);
        when(consultaGateway.criarConsulta(any(Consulta.class))).thenReturn(0L);

        assertThrows(RuntimeException.class, () -> criarConsultaService.save(command));
    }
}
