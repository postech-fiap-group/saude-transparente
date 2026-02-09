package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletarConsultaServiceTest {

    @Mock
    private ConsultaGateway consultaGateway;

    @InjectMocks
    private DeletarConsultaService deletarConsultaService;

    @Test
    void deveDeletarConsultaComSucesso() {
        Long id = 1L;

        assertDoesNotThrow(() -> deletarConsultaService.execute(id));

        verify(consultaGateway).deletarConsulta(id);
    }
}
