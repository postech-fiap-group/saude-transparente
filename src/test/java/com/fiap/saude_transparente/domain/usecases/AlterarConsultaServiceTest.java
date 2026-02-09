package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.AlterarConsultaCommand;
import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
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
class AlterarConsultaServiceTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @InjectMocks
    private AlterarConsultaService alterarConsultaService;

    @Test
    void deveAlterarConsultaComSucesso() {
        AlterarConsultaCommand command = new AlterarConsultaCommand(
                1L, 2L, 3L, LocalDateTime.now().plusDays(1), "Retorno");

        when(consultaGateway.alterarConsulta(any(Consulta.class))).thenReturn(1L);

        assertDoesNotThrow(() -> alterarConsultaService.save(command));

        verify(consultaGateway).alterarConsulta(any(Consulta.class));
    }

    @Test
    void deveLancarExcecaoQuandoFalhar() {
        AlterarConsultaCommand command = new AlterarConsultaCommand(
                1L, 2L, 3L, LocalDateTime.now().plusDays(1), "Retorno");

        when(consultaGateway.alterarConsulta(any(Consulta.class))).thenReturn(0L);

        assertThrows(RuntimeException.class, () -> alterarConsultaService.save(command));
    }
}
