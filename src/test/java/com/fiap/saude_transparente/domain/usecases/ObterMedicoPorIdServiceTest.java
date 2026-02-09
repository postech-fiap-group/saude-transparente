package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ObterMedicoPorIdServiceTest {

    @Mock
    private MedicoGateway medicoGateway;

    @InjectMocks
    private ObterMedicoPorIdService obterMedicoPorIdService;

    @Test
    void deveRetornarMedicoPorId() {
        Long id = 1L;
        Medico medico = new Medico();
        medico.setId(id);

        when(medicoGateway.getMedicoById(id)).thenReturn(medico);

        Medico result = obterMedicoPorIdService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(medicoGateway).getMedicoById(id);
    }
}
