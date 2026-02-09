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
class GetByIdContaInteractorTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private GetByIdContaInteractor getByIdContaInteractor;

    @Test
    void deveBuscarContaPorId() {
        Long id = 1L;
        Conta contaMock = new Conta();
        contaMock.setId(id);

        Mockito.when(contaGateway.findById(id)).thenReturn(contaMock);

        Conta resultado = getByIdContaInteractor.getById(id);

        Assertions.assertEquals(id, resultado.getId());
        Mockito.verify(contaGateway).findById(id);
    }
}
