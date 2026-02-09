package com.totvs.contasservice.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class ContaTest {

    @Test
    void deveCriarContaValida() {
        LocalDate hoje = LocalDate.now();
        Conta conta = new Conta(
                1L,
                hoje,
                hoje,
                100.0,
                "Conta de Luz",
                Situacao.PAGO);

        Assertions.assertNotNull(conta);
        Assertions.assertEquals(1L, conta.getId());
        Assertions.assertEquals(hoje, conta.getDataVencimento());
        Assertions.assertEquals(100.0, conta.getValor());
        Assertions.assertEquals(Situacao.PAGO, conta.getSituacao());
    }

    @Test
    void deveAtualizarSituacaoDaConta() {
        Conta conta = new Conta();
        conta.setSituacao(Situacao.PENDENTE);

        Assertions.assertEquals(Situacao.PENDENTE, conta.getSituacao());

        conta.setSituacao(Situacao.PAGO);
        Assertions.assertEquals(Situacao.PAGO, conta.getSituacao());
    }
}
