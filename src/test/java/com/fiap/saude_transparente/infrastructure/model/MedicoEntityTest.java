package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.enums.Especialidades;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MedicoEntityTest {

    @Test
    void deveCriarEntityAPartirDeDominio() {
        Medico medico = new Medico(1L, "Dr. House", "Gregory", "CARDIOLOGIA",
                "123456", "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));

        MedicoEntity entity = new MedicoEntity(medico);

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals("Dr. House", entity.getNome());
        assertEquals("Gregory", entity.getSobrenome());
        assertEquals(Especialidades.CARDIOLOGIA, entity.getEspecialidade());
        assertEquals("123456", entity.getCrm());
    }

    @Test
    void deveConverterEntityParaDominio() {
        MedicoEntity entity = new MedicoEntity();
        entity.setId(1L);
        entity.setNome("Dr. House");
        entity.setSobrenome("Gregory");
        entity.setEspecialidade(Especialidades.CARDIOLOGIA);
        entity.setCrm("123456");
        entity.setEndereco("Princeton Any");
        entity.setEmail("house@hospital.com");
        entity.setTelefone("123456789");
        entity.setCpf("11122233344");
        entity.setDataNascimento(LocalDate.of(1959, 6, 11));

        Medico medico = entity.toDomain();

        assertNotNull(medico);
        assertEquals(1L, medico.getId());
        assertEquals("Dr. House", medico.getNome());
        assertEquals("Gregory", medico.getSobrenome());
    }

    @Test
    void deveUsarConstrutorVazio() {
        MedicoEntity entity = new MedicoEntity();
        assertNotNull(entity);
        assertNull(entity.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        MedicoEntity entity = new MedicoEntity(
                1L, "Dr. House", "Gregory", Especialidades.CARDIOLOGIA,
                "123456", "Princeton Any", "house@hospital.com", "123456789",
                "11122233344", LocalDate.of(1959, 6, 11));

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
    }

    @Test
    void deveDefinirEObterPropriedades() {
        MedicoEntity entity = new MedicoEntity();

        entity.setId(1L);
        entity.setNome("João");
        entity.setSobrenome("Silva");
        entity.setEspecialidade(Especialidades.PEDIATRIA);
        entity.setCrm("12345");
        entity.setEndereco("Rua A");
        entity.setEmail("joao@email.com");
        entity.setTelefone("11999999999");
        entity.setCpf("12345678900");
        entity.setDataNascimento(LocalDate.of(1980, 1, 1));

        assertEquals(1L, entity.getId());
        assertEquals("João", entity.getNome());
        assertEquals("Silva", entity.getSobrenome());
        assertEquals(Especialidades.PEDIATRIA, entity.getEspecialidade());
    }
}
