package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.Situacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class CreateContaInteractorTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private CreateContaInteractor createContaInteractor;

    @Test
    void deveCriarContaComSucesso() {
        Conta contaInput = new Conta(null, LocalDate.now(), LocalDate.now(), 100.0, "Teste", Situacao.PAGO);
        Conta contaOutput = new Conta(1L, LocalDate.now(), LocalDate.now(), 100.0, "Teste", Situacao.PAGO);

        Mockito.when(contaGateway.createConta(contaInput)).thenReturn(contaOutput);

        Conta resultado = createContaInteractor.createConta(contaInput);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1L, resultado.getId());
        Mockito.verify(contaGateway).createConta(contaInput);
    }
}
