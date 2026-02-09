package com.totvs.contasservice.application.usecases.usuario;

import com.totvs.contasservice.application.gateways.UsuarioGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LoginInteractorTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @InjectMocks
    private LoginInteractor loginInteractor;

    @Test
    void deveRealizarLoginComSucesso() {
        String email = "teste@email.com";
        String senha = "senha";
        String tokenEsperado = "token.jwt.valid";

        Mockito.when(usuarioGateway.login(email, senha)).thenReturn(tokenEsperado);

        String token = loginInteractor.login(email, senha);

        Assertions.assertEquals(tokenEsperado, token);
        Mockito.verify(usuarioGateway).login(email, senha);
    }
}
