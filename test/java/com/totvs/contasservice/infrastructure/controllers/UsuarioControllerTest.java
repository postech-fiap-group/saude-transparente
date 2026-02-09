package com.totvs.contasservice.infrastructure.controllers;

import com.totvs.contasservice.application.usecases.usuario.CreateUsuarioUseCase;
import com.totvs.contasservice.application.usecases.usuario.GetAllUsuarioUseCase;
import com.totvs.contasservice.application.usecases.usuario.LoginUseCase;
import com.totvs.contasservice.domain.entity.Role;
import com.totvs.contasservice.domain.entity.Usuario;
import com.totvs.contasservice.infrastructure.controllers.dto.LoginRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.LoginResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioResponse;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.LoginDTOMapper;
import com.totvs.contasservice.infrastructure.controllers.dto.mappers.UsuarioDTOMapper;
import com.totvs.contasservice.infrastructure.persistence.UsuarioRepository;
import com.totvs.contasservice.infrastructure.security.JwtTokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    @Mock
    private CreateUsuarioUseCase createUsuarioUseCase;
    @Mock
    private GetAllUsuarioUseCase getAllUsuarioUseCase;
    @Mock
    private LoginUseCase loginUseCase;
    @Mock
    private UsuarioDTOMapper usuarioDTOMapper;
    @Mock
    private LoginDTOMapper loginDTOMapper;
    @Mock
    private JwtTokenService jwtTokenService;
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioController usuarioController;

    @Test
    void deveCriarUsuario() {
        UsuarioRequest request = new UsuarioRequest("teste@email.com", "senha", Role.USER);
        Usuario usuario = new Usuario(1L, "teste@email.com", "senha", Role.USER);
        UsuarioResponse response = new UsuarioResponse(1L, "teste@email.com", Role.USER);

        Mockito.when(usuarioDTOMapper.toUsuario(Mockito.any(UsuarioRequest.class))).thenReturn(usuario);
        Mockito.when(createUsuarioUseCase.createUsuario(Mockito.any(Usuario.class))).thenReturn(usuario);
        Mockito.when(usuarioDTOMapper.toResponse(Mockito.any(Usuario.class))).thenReturn(response);

        ResponseEntity<UsuarioResponse> result = usuarioController.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveFazerLogin() {
        LoginRequest request = new LoginRequest("teste@email.com", "senha");
        String token = "jwt_token";
        LoginResponse response = new LoginResponse(token);

        Mockito.when(loginUseCase.login(request.email(), request.senha())).thenReturn(token);
        Mockito.when(loginDTOMapper.toResponse(token)).thenReturn(response);

        ResponseEntity<LoginResponse> result = usuarioController.login(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void deveBuscarTodosUsuarios() {
        // Arrange
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);

        Usuario usuario = new Usuario(1L, "teste@email.com", "senha", Role.USER);
        UsuarioResponse usuarioResponse = new UsuarioResponse(1L, "teste@email.com", Role.USER);
        List<Usuario> usuarios = Collections.singletonList(usuario);

        Mockito.when(getAllUsuarioUseCase.getAllUsuario(page, size)).thenReturn(usuarios);
        Mockito.when(usuarioDTOMapper.toResponse(usuario)).thenReturn(usuarioResponse);

        // Act
        ResponseEntity<Page<UsuarioResponse>> result = usuarioController.findAll(pageable);

        // Assert
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(1, result.getBody().getContent().size());
        assertEquals(usuarioResponse, result.getBody().getContent().get(0));
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHouverUsuarios() {
        int page = 0;
        int size = 5;
        Pageable pageable = PageRequest.of(page, size);

        Mockito.when(getAllUsuarioUseCase.getAllUsuario(page, size)).thenReturn(Collections.emptyList());

        ResponseEntity<Page<UsuarioResponse>> result = usuarioController.findAll(pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
        assertEquals(0, result.getBody().getContent().size());
    }

    @Test
    void deveLancarExcecaoQuandoCriacaoFalhar() {
        UsuarioRequest request = new UsuarioRequest("teste@email.com", "senha", Role.USER);
        Usuario usuario = new Usuario(null, "teste@email.com", "senha", Role.USER);

        Mockito.when(usuarioDTOMapper.toUsuario(request)).thenReturn(usuario);
        Mockito.when(createUsuarioUseCase.createUsuario(usuario)).thenThrow(new RuntimeException("Erro ao criar"));

        assertThrows(RuntimeException.class, () -> usuarioController.create(request));
    }
}
