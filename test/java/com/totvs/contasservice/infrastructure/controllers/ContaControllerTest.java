package com.totvs.contasservice.infrastructure.controllers;

import com.totvs.contasservice.application.usecases.conta.*;
import com.totvs.contasservice.domain.entity.Conta;
import com.totvs.contasservice.domain.entity.ContaFiltro;
import com.totvs.contasservice.domain.entity.Situacao;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.UpdateSituacaoRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.ContaResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.TotalPagoResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.ContaDTOMapper;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.TotalPagoMapper;
import com.totvs.contasservice.infrastructure.persistence.UsuarioRepository;
import com.totvs.contasservice.infrastructure.security.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ContaControllerTest {

    @Mock
    private CreateContaUseCase createContaUseCase;
    @Mock
    private GetAllContaUseCase getAllContaUseCase;
    @Mock
    private GetByIdContaUseCase getByIdContaUseCase;
    @Mock
    private UpdateContaUseCase updateContaUseCase;
    @Mock
    private DeleteContaUseCase deleteContaUseCase;
    @Mock
    private ImportContasFromCsvUseCase importContasFromCsvUseCase;
    @Mock
    private GetValorTotalPagoPorPeriodoUseCase getValorTotalPagoPorPeriodoUseCase;
    @Mock
    private ContaDTOMapper contaDTOMapper;
    @Mock
    private TotalPagoMapper totalPagoMapper;
    @Mock
    private JwtTokenService jwtTokenService;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private UpdateSituacaoContaUseCase updateSituacaoContaUseCase;

    @InjectMocks
    private ContaController contaController;

    @Test
    void deveCriarConta() {
        LocalDate hoje = LocalDate.now();
        ContaRequest request = new ContaRequest(hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        Conta conta = new Conta(1L, hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        ContaResponse response = new ContaResponse(1L, hoje, hoje, 100.0, "Teste", Situacao.PAGO);

        Mockito.when(contaDTOMapper.toConta(Mockito.any(ContaRequest.class))).thenReturn(conta);
        Mockito.when(createContaUseCase.createConta(Mockito.any(Conta.class))).thenReturn(conta);
        Mockito.when(contaDTOMapper.toResponse(Mockito.any(Conta.class))).thenReturn(response);

        ResponseEntity<ContaResponse> result = contaController.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveImportarContasCsv() {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        LocalDate hoje = LocalDate.now();
        Conta conta = new Conta(1L, hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        ContaResponse response = new ContaResponse(1L, hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        List<Conta> contas = Collections.singletonList(conta);

        Mockito.when(importContasFromCsvUseCase.importCsv(file)).thenReturn(contas);
        Mockito.when(contaDTOMapper.toResponse(conta)).thenReturn(response);

        ResponseEntity<List<ContaResponse>> result = contaController.importCsv(file);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals(response, result.getBody().get(0));
    }

    @Test
    void deveBuscarTodasContas() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        LocalDate hoje = LocalDate.now();

        Conta conta = new Conta(1L, hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        ContaResponse response = new ContaResponse(1L, hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        List<Conta> contas = Collections.singletonList(conta);

        Mockito.when(getAllContaUseCase.getAllConta(Mockito.any(ContaFiltro.class), Mockito.eq(page), Mockito.eq(size)))
                .thenReturn(contas);
        Mockito.when(contaDTOMapper.toResponse(conta)).thenReturn(response);

        ResponseEntity<Page<ContaResponse>> result = contaController.findAll(null, null, pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().getContent().size());
        assertEquals(response, result.getBody().getContent().get(0));
    }

    @Test
    void deveBuscarContaPorId() {
        Long id = 1L;
        LocalDate hoje = LocalDate.now();
        Conta conta = new Conta(id, hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        ContaResponse response = new ContaResponse(id, hoje, hoje, 100.0, "Teste", Situacao.PAGO);

        Mockito.when(getByIdContaUseCase.getById(id)).thenReturn(conta);
        Mockito.when(contaDTOMapper.toResponse(conta)).thenReturn(response);

        ResponseEntity<ContaResponse> result = contaController.findById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveObterValorTotalPago() {
        LocalDate inicio = LocalDate.now().minusDays(10);
        LocalDate fim = LocalDate.now();
        Double total = 500.0;
        TotalPagoResponse response = new TotalPagoResponse(inicio, fim, total);

        Mockito.when(getValorTotalPagoPorPeriodoUseCase.execute(inicio, fim)).thenReturn(total);
        Mockito.when(totalPagoMapper.toResponse(inicio, fim, total)).thenReturn(response);

        ResponseEntity<TotalPagoResponse> result = contaController.getValorTotalPago(inicio, fim);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveRetornarBadRequestQuandoInicioForAposFim() {
        LocalDate inicio = LocalDate.now();
        LocalDate fim = LocalDate.now().minusDays(10);

        ResponseEntity<TotalPagoResponse> result = contaController.getValorTotalPago(inicio, fim);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void deveRetornarBadRequestQuandoDatasNulas() {
        ResponseEntity<TotalPagoResponse> result = contaController.getValorTotalPago(null, null);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void deveAtualizarConta() {
        Long id = 1L;
        LocalDate hoje = LocalDate.now();
        ContaRequest request = new ContaRequest(hoje, hoje, 150.0, "Teste Atualizado", Situacao.PAGO);
        Conta conta = new Conta(id, hoje, hoje, 150.0, "Teste Atualizado", Situacao.PAGO);
        ContaResponse response = new ContaResponse(id, hoje, hoje, 150.0, "Teste Atualizado", Situacao.PAGO);

        Mockito.when(contaDTOMapper.toConta(request)).thenReturn(conta);
        Mockito.when(updateContaUseCase.updateConta(id, conta)).thenReturn(conta);
        Mockito.when(contaDTOMapper.toResponse(conta)).thenReturn(response);

        ResponseEntity<ContaResponse> result = contaController.update(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveDeletarConta() {
        Long id = 1L;

        ResponseEntity<Void> result = contaController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        Mockito.verify(deleteContaUseCase).deleteConta(id);
    }

    @Test
    void deveImportarContasCsvVazio() {
        MultipartFile file = Mockito.mock(MultipartFile.class);
        Mockito.when(importContasFromCsvUseCase.importCsv(file)).thenReturn(Collections.emptyList());

        ResponseEntity<List<ContaResponse>> result = contaController.importCsv(file);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(0, result.getBody().size());
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverContas() {
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);

        Mockito.when(getAllContaUseCase.getAllConta(Mockito.any(ContaFiltro.class), Mockito.eq(page), Mockito.eq(size)))
                .thenReturn(Collections.emptyList());

        ResponseEntity<Page<ContaResponse>> result = contaController.findAll(null, null, pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(0, result.getBody().getContent().size());
    }

    @Test
    void deveLancarExcecaoQuandoCriacaoFalhar() {
        LocalDate hoje = LocalDate.now();
        ContaRequest request = new ContaRequest(hoje, hoje, 100.0, "Teste", Situacao.PAGO);
        Conta conta = new Conta(1L, hoje, hoje, 100.0, "Teste", Situacao.PAGO);

        Mockito.when(contaDTOMapper.toConta(request)).thenReturn(conta);
        Mockito.when(createContaUseCase.createConta(conta)).thenThrow(new RuntimeException("Erro ao criar"));

        assertThrows(RuntimeException.class, () -> contaController.create(request));
    }

    @Test
    void deveLancarExcecaoQuandoBuscarPorIdFalhar() {
        Long id = 1L;
        Mockito.when(getByIdContaUseCase.getById(id)).thenThrow(new RuntimeException("Conta não encontrada"));

        assertThrows(RuntimeException.class, () -> contaController.findById(id));
    }

    @Test
    void deveLancarExcecaoQuandoAtualizacaoFalhar() {
        Long id = 1L;
        LocalDate hoje = LocalDate.now();
        ContaRequest request = new ContaRequest(hoje, hoje, 100.0, "Teste Update", Situacao.PAGO);
        Conta conta = new Conta(id, hoje, hoje, 100.0, "Teste Update", Situacao.PAGO);

        Mockito.when(contaDTOMapper.toConta(request)).thenReturn(conta);
        Mockito.when(updateContaUseCase.updateConta(id, conta)).thenThrow(new RuntimeException("Erro ao atualizar"));

        assertThrows(RuntimeException.class, () -> contaController.update(id, request));
    }

    @Test
    void deveLancarExcecaoQuandoDelecaoFalhar() {
        Long id = 1L;
        Mockito.doThrow(new RuntimeException("Erro ao deletar")).when(deleteContaUseCase).deleteConta(id);

        assertThrows(RuntimeException.class, () -> contaController.delete(id));
    }

    @Test
    void deveRetornarBadRequestQuandoApenasInicioForNulo() {
        LocalDate fim = LocalDate.now();
        ResponseEntity<TotalPagoResponse> result = contaController.getValorTotalPago(null, fim);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void deveRetornarBadRequestQuandoApenasFimForNulo() {
        LocalDate inicio = LocalDate.now();
        ResponseEntity<TotalPagoResponse> result = contaController.getValorTotalPago(inicio, null);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    void deveAtualizarSituacao() {
        Long id = 1L;
        LocalDate hoje = LocalDate.now();
        Situacao novaSituacao = Situacao.PAGO;
        UpdateSituacaoRequest request = new UpdateSituacaoRequest(novaSituacao);
        Conta conta = new Conta(id, hoje, hoje, 100.0, "Teste", novaSituacao);
        ContaResponse response = new ContaResponse(id, hoje, hoje, 100.0, "Teste", novaSituacao);

        Mockito.when(updateSituacaoContaUseCase.updateSituacao(id, novaSituacao)).thenReturn(conta);
        Mockito.when(contaDTOMapper.toResponse(conta)).thenReturn(response);

        ResponseEntity<ContaResponse> result = contaController.updateSituacao(id, request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }
}
