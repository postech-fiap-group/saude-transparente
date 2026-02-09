package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterTodasAvaliacoesServiceTest {

    @Mock
    private AvaliacaoGateway avaliacaoGateway;

    @InjectMocks
    private ObterTodasAvaliacoesService obterTodasAvaliacoesService;

    @Test
    void deveRetornarListaDeAvaliacoes() {
        Avaliacao avaliacao1 = new Avaliacao(1L, 1L, 5, "Otimo");
        Avaliacao avaliacao2 = new Avaliacao(2L, 2L, 4, "Bom");
        List<Avaliacao> avaliacoes = Arrays.asList(avaliacao1, avaliacao2);

        when(avaliacaoGateway.getAll(10, 0)).thenReturn(avaliacoes);

        List<Avaliacao> result = obterTodasAvaliacoesService.execute(1, 10);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(avaliacaoGateway).getAll(10, 0);
    }

    @Test
    void deveCalcularOffsetCorretamente() {
        when(avaliacaoGateway.getAll(10, 20)).thenReturn(Collections.emptyList());

        obterTodasAvaliacoesService.execute(3, 10);

        verify(avaliacaoGateway).getAll(10, 20);
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverAvaliacoes() {
        when(avaliacaoGateway.getAll(10, 0)).thenReturn(Collections.emptyList());

        List<Avaliacao> result = obterTodasAvaliacoesService.execute(1, 10);

        assertNotNull(result);
        assertEquals(0, result.size());
    }
}
