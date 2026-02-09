package com.totvs.contasservice.application.usecases.usuario;

import com.totvs.contasservice.application.gateways.UsuarioGateway;
import com.totvs.contasservice.domain.entity.Role;
import com.totvs.contasservice.domain.entity.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreateUsuarioInteractorTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private CreateUsuarioInteractor createUsuarioInteractor;

    @Test
    void deveCriarUsuarioComSucesso() {
        Usuario usuarioInput = new Usuario(null, "teste@email.com", "senha", Role.USER);
        Usuario usuarioOutput = new Usuario(1L, "teste@email.com", "senha", Role.USER);

        Mockito.when(usuarioGateway.createUsuario(usuarioInput)).thenReturn(usuarioOutput);

        Usuario resultado = createUsuarioInteractor.createUsuario(usuarioInput);

        Assertions.assertNotNull(resultado);
        Assertions.assertEquals(1L, resultado.getId());
        Mockito.verify(usuarioGateway).createUsuario(usuarioInput);
    }
}
