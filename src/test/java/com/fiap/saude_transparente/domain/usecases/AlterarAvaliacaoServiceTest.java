package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.AlterarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlterarAvaliacaoServiceTest {

    @Mock
    private AvaliacaoGateway avaliacaoGateway;

    @InjectMocks
    private AlterarAvaliacaoService alterarAvaliacaoService;

    @Test
    void deveAlterarAvaliacaoComSucesso() {
        AlterarAvaliacaoCommand command = new AlterarAvaliacaoCommand(1L, 1L, 4, "Bom");

        when(avaliacaoGateway.alterarAvaliacao(any(Avaliacao.class))).thenReturn(1L);

        assertDoesNotThrow(() -> alterarAvaliacaoService.save(command));

        verify(avaliacaoGateway).alterarAvaliacao(any(Avaliacao.class));
    }

    @Test
    void deveLancarExcecaoQuandoFalhar() {
        AlterarAvaliacaoCommand command = new AlterarAvaliacaoCommand(1L, 1L, 4, "Bom");

        when(avaliacaoGateway.alterarAvaliacao(any(Avaliacao.class))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> alterarAvaliacaoService.save(command));
    }

    @Test
    void deveLancarExcecaoQuandoIdForZero() {
        AlterarAvaliacaoCommand command = new AlterarAvaliacaoCommand(1L, 1L, 4, "Bom");

        when(avaliacaoGateway.alterarAvaliacao(any(Avaliacao.class))).thenReturn(0L);

        assertThrows(RuntimeException.class, () -> alterarAvaliacaoService.save(command));
    }
}
