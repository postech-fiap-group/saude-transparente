package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
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
class ObterConsultasServiceTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @InjectMocks
    private ObterConsultasService obterConsultasService;

    @Test
    void deveRetornarListaDeConsultas() {
        Consulta consulta1 = new Consulta();
        Consulta consulta2 = new Consulta();
        List<Consulta> consultas = Arrays.asList(consulta1, consulta2);

        when(consultaGateway.getAllConsultas(0, 10)).thenReturn(consultas);

        List<Consulta> result = obterConsultasService.execute(0, 10);

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(consultaGateway).getAllConsultas(0, 10);
    }
}
