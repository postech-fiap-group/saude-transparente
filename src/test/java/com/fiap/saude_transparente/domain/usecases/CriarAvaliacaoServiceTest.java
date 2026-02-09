package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarAvaliacaoCommand;
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
class CriarAvaliacaoServiceTest {

    @Mock
    private AvaliacaoGateway avaliacaoGateway;

    @InjectMocks
    private CriarAvaliacaoService criarAvaliacaoService;

    @Test
    void deveCriarAvaliacaoComSucesso() {
        CriarAvaliacaoCommand command = new CriarAvaliacaoCommand(1L, 5, "Otimo");

        when(avaliacaoGateway.criarAvaliacao(any(Avaliacao.class))).thenReturn(1L);

        assertDoesNotThrow(() -> criarAvaliacaoService.save(command));

        verify(avaliacaoGateway).criarAvaliacao(any(Avaliacao.class));
    }

    @Test
    void deveLancarExcecaoQuandoFalhar() {
        CriarAvaliacaoCommand command = new CriarAvaliacaoCommand(1L, 5, "Otimo");

        when(avaliacaoGateway.criarAvaliacao(any(Avaliacao.class))).thenReturn(0L);

        assertThrows(RuntimeException.class, () -> criarAvaliacaoService.save(command));
    }
}
