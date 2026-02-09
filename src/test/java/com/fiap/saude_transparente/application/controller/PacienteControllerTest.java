package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarPacienteDTO;
import com.fiap.saude_transparente.domain.commands.AlterarPacienteCommand;
import com.fiap.saude_transparente.domain.commands.CriarPacienteCommand;
import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.usecases.AlterarPacienteService;
import com.fiap.saude_transparente.domain.usecases.CriarPacienteService;
import com.fiap.saude_transparente.domain.usecases.DeletarPacienteService;
import com.fiap.saude_transparente.domain.usecases.ObterPacientePorIdService;
import com.fiap.saude_transparente.domain.usecases.ObterPacientesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PacienteControllerTest {

    @Mock
    private CriarPacienteService criarPacienteService;
    @Mock
    private AlterarPacienteService alterarPacienteService;
    @Mock
    private ObterPacientesService obterPacientesService;
    @Mock
    private ObterPacientePorIdService obterPacientePorIdService;
    @Mock
    private DeletarPacienteService deletarPacienteService;
    @Mock
    private Validator validator;

    @InjectMocks
    private PacienteController pacienteController;

    private CriarPacienteDTO criarDto() {
        return new CriarPacienteDTO(
                "João", "Silva", "Rua X", "joao@email.com",
                "11999998888", "12345678900", LocalDate.of(1990, 1, 1));
    }

    @Test
    void criar() {
        CriarPacienteDTO dto = criarDto();

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<String> result = pacienteController.criar(dto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(criarPacienteService).save(any(CriarPacienteCommand.class));
    }

    @Test
    void alterar() {
        Long id = 1L;
        CriarPacienteDTO dto = criarDto();

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<String> result = pacienteController.alterar(id, dto);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(alterarPacienteService).save(any(AlterarPacienteCommand.class));
    }

    @Test
    void getAll() {
        Paciente paciente = new Paciente();
        List<Paciente> pacientes = Collections.singletonList(paciente);

        when(obterPacientesService.execute(1, 10)).thenReturn(pacientes);

        ResponseEntity<List<Paciente>> result = pacienteController.getAll(1, 10);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void getById() {
        Long id = 1L;
        Paciente paciente = new Paciente();

        when(obterPacientePorIdService.execute(id)).thenReturn(paciente);

        ResponseEntity<Paciente> result = pacienteController.getById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(paciente, result.getBody());
    }

    @Test
    void delete() {
        Long id = 1L;

        ResponseEntity<String> result = pacienteController.delete(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(deletarPacienteService).execute(id);
    }
}
