package com.totvs.contasservice.infrastructure.controllers.dto.mappers;

import com.totvs.contasservice.domain.entity.Role;
import com.totvs.contasservice.domain.entity.Usuario;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioRequest;
import com.totvs.contasservice.infrastructure.controllers.dto.UsuarioResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UsuarioDTOMapperTest {

    private final UsuarioDTOMapper mapper = new UsuarioDTOMapper();

    @Test
    void deveMapearRequestParaUsuario() {
        UsuarioRequest request = new UsuarioRequest("teste@email.com", "senha", Role.USER);

        Usuario usuario = mapper.toUsuario(request);

        Assertions.assertEquals(request.email(), usuario.getEmail());
        Assertions.assertEquals(request.senha(), usuario.getSenha());
        Assertions.assertEquals(request.role(), usuario.getRole());
    }

    @Test
    void deveMapearUsuarioParaResponse() {
        Usuario usuario = new Usuario(1L, "teste@email.com", "senha", Role.USER);

        UsuarioResponse response = mapper.toResponse(usuario);

        Assertions.assertEquals(usuario.getId(), response.id());
        Assertions.assertEquals(usuario.getEmail(), response.email());
        Assertions.assertEquals(usuario.getRole(), response.role());
    }
}
