package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarAvaliacaoDTO;
import com.fiap.saude_transparente.domain.commands.AlterarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.commands.CriarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.exceptions.InvalidFieldsException;
import com.fiap.saude_transparente.domain.usecases.AlterarAvaliacaoService;
import com.fiap.saude_transparente.domain.usecases.CriarAvaliacaoService;
import com.fiap.saude_transparente.domain.usecases.ObterAvaliacaoPorId;
import com.fiap.saude_transparente.domain.usecases.ObterTodasAvaliacoesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AvaliacaoControllerTest {

    @Mock
    private CriarAvaliacaoService criarAvaliacaoService;
    @Mock
    private AlterarAvaliacaoService alterarAvaliacaoService;
    @Mock
    private ObterTodasAvaliacoesService obterTodasAvaliacoesService;
    @Mock
    private ObterAvaliacaoPorId obterAvaliacaoPorId;
    @Mock
    private Validator validator;

    @InjectMocks
    private AvaliacaoController avaliacaoController;

    @Test
    void deveAvaliar() {
        CriarAvaliacaoDTO dto = new CriarAvaliacaoDTO(1L, 5, "Otimo atendimento");

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<Void> result = avaliacaoController.avaliar(dto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(criarAvaliacaoService).save(any(CriarAvaliacaoCommand.class));
    }

    @Test
    void deveAlterar() {
        Long id = 1L;
        CriarAvaliacaoDTO dto = new CriarAvaliacaoDTO(1L, 4, "Bom atendimento");

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<Void> result = avaliacaoController.alterar(id, dto);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(alterarAvaliacaoService).save(any(AlterarAvaliacaoCommand.class));
    }

    @Test
    void deveBuscarTodasAvaliacoes() {
        Avaliacao avaliacao = new Avaliacao(); // Assuming default constructor exists
        List<Avaliacao> avaliacoes = Collections.singletonList(avaliacao);

        when(obterTodasAvaliacoesService.execute(1, 10)).thenReturn(avaliacoes);

        ResponseEntity<List<Avaliacao>> result = avaliacaoController.getAllAvaliacao(1, 10);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals(avaliacao, result.getBody().get(0));
    }

    @Test
    void deveBuscarPorId() {
        Long id = 1L;
        Avaliacao avaliacao = new Avaliacao();

        when(obterAvaliacaoPorId.execute(id)).thenReturn(avaliacao);

        ResponseEntity<Avaliacao> result = avaliacaoController.getById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(avaliacao, result.getBody());
    }

//    @Test
//    void deveLancarExcecaoQuandoValidacaoFalhar() {
//        CriarAvaliacaoDTO dto = new CriarAvaliacaoDTO(1L, 5, "Otimo atendimento");
//
//        // Mock validator failure
//        Errors errors = mock(Errors.class);
//        when(validator.validateObject(any())).thenReturn(errors);
//    }
}
