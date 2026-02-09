package com.totvs.contasservice.domain.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UsuarioTest {

    @Test
    void deveCriarUsuarioValido() {
        Usuario usuario = new Usuario(1L, "teste@email.com", "senha123", Role.USER);

        Assertions.assertNotNull(usuario);
        Assertions.assertEquals(1L, usuario.getId());
        Assertions.assertEquals("teste@email.com", usuario.getEmail());
        Assertions.assertEquals("senha123", usuario.getSenha());
        Assertions.assertEquals(Role.USER, usuario.getRole());
    }

    @Test
    void deveAtualizarDadosDoUsuario() {
        Usuario usuario = new Usuario(1L, "teste@email.com", "senha123", Role.USER);

        usuario.setEmail("novo@email.com");
        usuario.setSenha("novasenha");
        usuario.setRole(Role.ADMIN);

        Assertions.assertEquals("novo@email.com", usuario.getEmail());
        Assertions.assertEquals("novasenha", usuario.getSenha());
        Assertions.assertEquals(Role.ADMIN, usuario.getRole());
    }
}
