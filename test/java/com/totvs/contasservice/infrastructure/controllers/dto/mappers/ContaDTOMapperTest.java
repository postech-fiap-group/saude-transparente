package com.totvs.contasservice.infrastructure.controllers.dto.mappers;

import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.Situacao;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

class ContaDTOMapperTest {

    private final ContaDTOMapper mapper = new ContaDTOMapper();

    @Test
    void deveMapearRequestParaConta() {
        ContaRequest request = new ContaRequest(LocalDate.now(), LocalDate.now(), 100.0, "Teste", Situacao.PAGO);

        Conta conta = mapper.toConta(request);

        Assertions.assertEquals(request.dataVencimento(), conta.getDataVencimento());
        Assertions.assertEquals(request.valor(), conta.getValor());
        Assertions.assertEquals(request.descricao(), conta.getDescricao());
        Assertions.assertEquals(request.situacao(), conta.getSituacao());
    }

    @Test
    void deveMapearContaParaResponse() {
        Conta conta = new Conta(1L, LocalDate.now(), LocalDate.now(), 100.0, "Teste", Situacao.PAGO);

        ContaResponse response = mapper.toResponse(conta);

        Assertions.assertEquals(conta.getId(), response.id());
        Assertions.assertEquals(conta.getDataVencimento(), response.dataVencimento());
        Assertions.assertEquals(conta.getValor(), response.valor());
        Assertions.assertEquals(conta.getDescricao(), response.descricao());
        Assertions.assertEquals(conta.getSituacao(), response.situacao());
    }
}
