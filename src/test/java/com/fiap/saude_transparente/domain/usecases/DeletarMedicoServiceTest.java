package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.gateway.MedicoGateway;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DeletarMedicoServiceTest {

    @Mock
    private MedicoGateway medicoGateway;

    @InjectMocks
    private DeletarMedicoService deletarMedicoService;

    @Test
    void deveDeletarMedicoComSucesso() {
        Long id = 1L;

        assertDoesNotThrow(() -> deletarMedicoService.deletar(id));

        verify(medicoGateway).deletarMedico(id);
    }
}
