package com.totvs.contasservice.application.usecases.conta;

import com.totvs.contasservice.application.gateways.ContaGateway;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.Situacao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UpdateSituacaoContaInteractorTest {

    @Mock
    private ContaGateway contaGateway;

    @InjectMocks
    private UpdateSituacaoContaInteractor updateSituacaoContaInteractor;

    @Test
    void deveAtualizarSituacaoDaConta() {
        Long id = 1L;
        Situacao novaSituacao = Situacao.PAGO;
        LocalDate hoje = LocalDate.now();
        Conta contaAtualizada = new Conta(id, hoje, hoje, 100.0, "Teste", novaSituacao);

        updateSituacaoContaInteractor.updateSituacao(id, novaSituacao);

        verify(contaGateway).updateSituacao(id, novaSituacao);
    }
}
