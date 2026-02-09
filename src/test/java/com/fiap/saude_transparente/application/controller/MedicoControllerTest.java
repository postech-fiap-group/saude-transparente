package com.fiap.saude_transparente.application.controller;

import com.fiap.saude_transparente.application.controller.dtos.CriarMedicoDTO;
import com.fiap.saude_transparente.domain.commands.AlterarMedicoCommand;
import com.fiap.saude_transparente.domain.commands.CriarMedicoCommand;
import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.usecases.AlterarMedicoService;
import com.fiap.saude_transparente.domain.usecases.CriarMedicoService;
import com.fiap.saude_transparente.domain.usecases.DeletarMedicoService;
import com.fiap.saude_transparente.domain.usecases.ObterMedicoPorIdService;
import com.fiap.saude_transparente.domain.usecases.ObterMedicosService;
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
class MedicoControllerTest {

    @Mock
    private CriarMedicoService criarMedicoService;
    @Mock
    private AlterarMedicoService alterarMedicoService;
    @Mock
    private DeletarMedicoService deletarMedicoService;
    @Mock
    private ObterMedicosService obterMedicosService;
    @Mock
    private ObterMedicoPorIdService obterMedicoPorIdService;
    @Mock
    private Validator validator;

    @InjectMocks
    private MedicoController medicoController;

    private CriarMedicoDTO criarMedicoDTO() {
        return new CriarMedicoDTO(
                "Dr. House", "Gregory", "Diagnosta", "123456",
                "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));
    }

    @Test
    void findAllMedico() {
        Medico medico = new Medico();
        List<Medico> medicos = Collections.singletonList(medico);

        when(obterMedicosService.findAll(1, 10)).thenReturn(medicos);

        ResponseEntity<List<Medico>> result = medicoController.findAllMedico(1, 10);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void findMedicoById() {
        Long id = 1L;
        Medico medico = new Medico();

        when(obterMedicoPorIdService.findById(id)).thenReturn(medico);

        ResponseEntity<Medico> result = medicoController.findMedicoById(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(medico, result.getBody());
    }

    @Test
    void createMedico() {
        CriarMedicoDTO dto = criarMedicoDTO();

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<Void> result = medicoController.createMedico(dto);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        verify(criarMedicoService).criar(any(CriarMedicoCommand.class));
    }

    @Test
    void updateMedico() {
        Long id = 1L;
        CriarMedicoDTO dto = criarMedicoDTO();

        // Mock validator
        Errors errors = mock(Errors.class);
        when(validator.validateObject(any())).thenReturn(errors);
        when(errors.getAllErrors()).thenReturn(Collections.emptyList());

        ResponseEntity<Void> result = medicoController.updateMedico(id, dto);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(alterarMedicoService).update(any(AlterarMedicoCommand.class));
    }

    @Test
    void deleteMedico() {
        Long id = 1L;

        ResponseEntity<Void> result = medicoController.deleteMedico(id);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(deletarMedicoService).deletar(id);
    }
}
