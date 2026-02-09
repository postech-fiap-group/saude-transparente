package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarConsultaDTO;
import com.fiap.saude_transparente.domain.commands.AlterarConsultaCommand;
import com.fiap.saude_transparente.domain.commands.CriarConsultaCommand;
import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.usecases.AlterarConsultaService;
import com.fiap.saude_transparente.domain.usecases.CriarConsultaService;
import com.fiap.saude_transparente.domain.usecases.DeletarConsultaService;
import com.fiap.saude_transparente.domain.usecases.ObterConsultaPorIdService;
import com.fiap.saude_transparente.domain.usecases.ObterConsultasService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConsultaControllerTest {

    @Mock
    private CriarConsultaService criarConsultaService;
    @Mock
    private AlterarConsultaService alterarConsultaService;
    @Mock
    private ObterConsultasService obterConsultasService;
    @Mock
    private ObterConsultaPorIdService obterConsultaPorIdService;
    @Mock
    private DeletarConsultaService deletarConsultaService;
    @Mock
    private Validator validator;

    @InjectMocks
    private ConsultaController consultaController;

    private CriarConsultaDTO criarDto() {
        return new CriarConsultaDTO(
                1L, 2L, LocalDateTime.now().plusDays(1), "Checkup anual");
    }

    @Test
    void criar() {
        CriarConsultaDTO dto = criarDto();

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<Void> result = consultaController.criar(dto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(criarConsultaService).save(any(CriarConsultaCommand.class));
    }

    @Test
    void alterar() {
        Long id = 1L;
        CriarConsultaDTO dto = criarDto();

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<Void> result = consultaController.alterar(id, dto);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(alterarConsultaService).save(any(AlterarConsultaCommand.class));
    }

    @Test
    void getAll() {
        Consulta consulta = new Consulta();
        List<Consulta> consultas = Collections.singletonList(consulta);

        when(obterConsultasService.execute(0, 10)).thenReturn(consultas);

        ResponseEntity<List<Consulta>> result = consultaController.getAll(0, 10);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void getById() {
        Long id = 1L;
        Consulta consulta = new Consulta();

        when(obterConsultaPorIdService.execute(id)).thenReturn(consulta);

        ResponseEntity<Consulta> result = consultaController.getById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(consulta, result.getBody());
    }

    @Test
    void delete() {
        Long id = 1L;

        ResponseEntity<Void> result = consultaController.delete(id);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(deletarConsultaService).execute(id);
    }
}
