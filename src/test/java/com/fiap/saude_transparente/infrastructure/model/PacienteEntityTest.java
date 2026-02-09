package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Paciente;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteEntityTest {

    @Test
    void deveCriarEntityAPartirDeDominio() {
        Paciente paciente = new Paciente(1L, "João", "Silva", "11999998888",
                "joao@email.com", "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        PacienteEntity entity = new PacienteEntity(paciente);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("João", entity.getNome());
        assertEquals("Silva", entity.getSobrenome());
        assertEquals("11999998888", entity.getTelefone());
    }

    @Test
    void deveConverterEntityParaDominio() {
        PacienteEntity entity = new PacienteEntity();
        entity.setId(1L);
        entity.setNome("Maria");
        entity.setSobrenome("Santos");
        entity.setTelefone("11888887777");
        entity.setEmail("maria@email.com");
        entity.setCpf("98765432100");
        entity.setEndereco("Rua Y");
        entity.setDataNascimento(LocalDate.of(1985, 5, 15));

        Paciente paciente = entity.toDomain();

        assertNotNull(paciente);
        assertEquals(1L, paciente.getId());
        assertEquals("Maria", paciente.getNome());
    }

    @Test
    void deveUsarConstrutorVazio() {
        PacienteEntity entity = new PacienteEntity();
        assertNotNull(entity);
        assertNull(entity.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        PacienteEntity entity = new PacienteEntity(
                1L, "João", "Silva", "11999998888",
                "joao@email.com", "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
    }
}
