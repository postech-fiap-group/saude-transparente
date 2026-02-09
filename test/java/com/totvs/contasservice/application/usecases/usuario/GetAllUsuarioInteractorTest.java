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

import java.util.List;

@ExtendWith(MockitoExtension.class)
class GetAllUsuarioInteractorTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private GetAllUsuarioInteractor getAllUsuarioInteractor;

    @Test
    void deveListarUsuarios() {
        Usuario usuario = new Usuario(1L, "teste@email.com", "senha", Role.USER);
        List<Usuario> listaMock = List.of(usuario);

        Mockito.when(usuarioGateway.getAllUsuario(0, 10)).thenReturn(listaMock);

        List<Usuario> resultado = getAllUsuarioInteractor.getAllUsuario(0, 10);

        Assertions.assertFalse(resultado.isEmpty());
        Assertions.assertEquals(1, resultado.size());
        Mockito.verify(usuarioGateway).getAllUsuario(0, 10);
    }
}
