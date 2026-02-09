package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UpdateContaInteractorTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private UpdateContaInteractor updateContaInteractor;

    @Test
    void deveAtualizarContaComSucesso() {
        Long id = 1L;
        Conta contaInput = new Conta();
        Conta contaOutput = new Conta();
        contaOutput.setId(id);

        Mockito.when(contaGateway.update(id, contaInput)).thenReturn(contaOutput);

        Conta resultado = updateContaInteractor.updateConta(id, contaInput);

        Assertions.assertEquals(id, resultado.getId());
        Mockito.verify(contaGateway).update(id, contaInput);
    }
}
