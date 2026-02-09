package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
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
class ObterConsultaPorIdServiceTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @InjectMocks
    private ObterConsultaPorIdService obterConsultaPorIdService;

    @Test
    void deveRetornarConsultaPorId() {
        Long id = 1L;
        Consulta consulta = new Consulta();
        consulta.setId(id);

        when(consultaGateway.getConsultaById(id)).thenReturn(consulta);

        Consulta result = obterConsultaPorIdService.execute(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(consultaGateway).getConsultaById(id);
    }
}
