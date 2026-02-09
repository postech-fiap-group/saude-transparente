package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterMedicosServiceTest {

    @Mock
    private MedicoGateway medicoGateway;

    @InjectMocks
    private ObterMedicosService obterMedicosService;

    @Test
    void deveRetornarListaDeMedicos() {
        Medico medico1 = new Medico();
        Medico medico2 = new Medico();
        List<Medico> medicos = Arrays.asList(medico1, medico2);

        when(medicoGateway.getAllMedicos(10, 0)).thenReturn(medicos);

        List<Medico> result = obterMedicosService.findAll(1, 10);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(medicoGateway).getAllMedicos(10, 0);
    }
}
