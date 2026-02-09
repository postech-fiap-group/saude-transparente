package com.fiap.saude_transparente.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicoTest {

    @Test
    void deveCriarMedico() {
        Medico medico = Medico.criar(
                "Dr. House", "Gregory", "Diagnosta", "123456",
                "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));

        assertNotNull(medico);
        assertEquals("Dr. House", medico.getNome());
        assertEquals("Gregory", medico.getSobrenome());
        assertEquals("Diagnosta", medico.getEspecialidade());
        assertEquals("123456", medico.getCrm());
        assertEquals("Princeton Any", medico.getEndereco());
        assertEquals("house@hospital.com", medico.getEmail());
        assertEquals("123456789", medico.getTelefone());
        assertEquals("11122233344", medico.getCpf());
        assertEquals(LocalDate.of(1959, 6, 11), medico.getDataNascimento());
    }

    @Test
    void deveAlterarMedico() {
        Medico medico = Medico.alterar(
                1L, "Dr. House", "Gregory", "Diagnosta", "123456",
                "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));

        assertNotNull(medico);
        assertEquals(1L, medico.getId());
        assertEquals("Dr. House", medico.getNome());
        assertEquals("Gregory", medico.getSobrenome());
    }

    @Test
    void deveUsarConstrutorVazio() {
        Medico medico = new Medico();
        assertNotNull(medico);
        assertNull(medico.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        Medico medico = new Medico(
                1L, "Dr. House", "Gregory", "Diagnosta", "123456",
                "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));

        assertNotNull(medico);
        assertEquals(1L, medico.getId());
    }

    @Test
    void deveDefinirEObterPropriedades() {
        Medico medico = new Medico();
        medico.setId(1L);
        medico.setNome("João");
        medico.setSobrenome("Silva");
        medico.setEspecialidade("Cardiologia");
        medico.setCrm("12345");
        medico.setEndereco("Rua A");
        medico.setEmail("joao@email.com");
        medico.setTelefone("11999999999");
        medico.setCpf("12345678900");
        medico.setDataNascimento(LocalDate.of(1980, 1, 1));

        assertEquals(1L, medico.getId());
        assertEquals("João", medico.getNome());
        assertEquals("Silva", medico.getSobrenome());
        assertEquals("Cardiologia", medico.getEspecialidade());
        assertEquals("12345", medico.getCrm());
        assertEquals("Rua A", medico.getEndereco());
        assertEquals("joao@email.com", medico.getEmail());
        assertEquals("11999999999", medico.getTelefone());
        assertEquals("12345678900", medico.getCpf());
        assertEquals(LocalDate.of(1980, 1, 1), medico.getDataNascimento());
    }
}
