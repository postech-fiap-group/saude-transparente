package com.fiap.saude_transparente.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PacienteTest {

    @Test
    void deveCriarPaciente() {
        Paciente paciente = Paciente.criar(
                "João", "Silva", "11999998888", "joao@email.com",
                "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        assertNotNull(paciente);
        assertEquals("João", paciente.getNome());
        assertEquals("Silva", paciente.getSobrenome());
        assertEquals("11999998888", paciente.getTelefone());
        assertEquals("joao@email.com", paciente.getEmail());
        assertEquals("12345678900", paciente.getCpf());
        assertEquals("Rua X", paciente.getEndereco());
        assertEquals(LocalDate.of(1990, 1, 1), paciente.getDataNascimento());
    }

    @Test
    void deveAlterarPaciente() {
        Paciente paciente = Paciente.alterar(
                1L, "Maria", "Santos", "11888887777", "maria@email.com",
                "98765432100", "Rua Y", LocalDate.of(1985, 5, 15));

        assertNotNull(paciente);
        assertEquals(1L, paciente.getId());
        assertEquals("Maria", paciente.getNome());
        assertEquals("Santos", paciente.getSobrenome());
    }

    @Test
    void deveUsarConstrutorVazio() {
        Paciente paciente = new Paciente();
        assertNotNull(paciente);
        assertNull(paciente.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        Paciente paciente = new Paciente(
                1L, "João", "Silva", "11999998888", "joao@email.com",
                "12345678900", "Rua X", LocalDate.of(1990, 1, 1));

        assertNotNull(paciente);
        assertEquals(1L, paciente.getId());
    }

    @Test
    void deveDefinirEObterPropriedades() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNome("Carlos");
        paciente.setSobrenome("Oliveira");
        paciente.setTelefone("11777776666");
        paciente.setEmail("carlos@email.com");
        paciente.setCpf("11111111111");
        paciente.setEndereco("Rua Z");
        paciente.setDataNascimento(LocalDate.of(1995, 3, 20));

        assertEquals(1L, paciente.getId());
        assertEquals("Carlos", paciente.getNome());
        assertEquals("Oliveira", paciente.getSobrenome());
        assertEquals("11777776666", paciente.getTelefone());
        assertEquals("carlos@email.com", paciente.getEmail());
        assertEquals("11111111111", paciente.getCpf());
        assertEquals("Rua Z", paciente.getEndereco());
        assertEquals(LocalDate.of(1995, 3, 20), paciente.getDataNascimento());
    }
}
