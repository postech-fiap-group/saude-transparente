package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterAvaliacaoPorIdTest {

    @Mock
    private AvaliacaoGateway avaliacaoGateway;

    @InjectMocks
    private ObterAvaliacaoPorId obterAvaliacaoPorId;

    @Test
    void deveRetornarAvaliacaoPorId() {
        Long id = 1L;
        Avaliacao avaliacao = new Avaliacao(id, 1L, 5, "Excelente");

        when(avaliacaoGateway.getBydId(id)).thenReturn(avaliacao);

        Avaliacao result = obterAvaliacaoPorId.execute(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(5, result.getNota());
        verify(avaliacaoGateway).getBydId(id);
    }
}
