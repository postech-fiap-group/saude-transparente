package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class GetValorTotalPagoPorPeriodoInteractorTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private GetValorTotalPagoPorPeriodoInteractor interactor;

    @Test
    void deveRetornarValorTotal() {
        LocalDate inicio = LocalDate.now().minusDays(10);
        LocalDate fim = LocalDate.now();
        Double valorEsperado = 500.0;

        Mockito.when(contaGateway.getValorTotalPagoPorPeriodo(inicio, fim)).thenReturn(valorEsperado);

        Double resultado = interactor.execute(inicio, fim);

        Assertions.assertEquals(valorEsperado, resultado);
        Mockito.verify(contaGateway).getValorTotalPagoPorPeriodo(inicio, fim);
    }
}
