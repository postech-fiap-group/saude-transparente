package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Consulta;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsultaEntityTest {

    @Test
    void deveCriarEntityAPartirDeDominio() {
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 3, 15, 10, 0);
        Consulta consulta = new Consulta(null, 1L, 2L, dataConsulta, "Checkup");

        ConsultaEntity entity = new ConsultaEntity(consulta);

        assertNotNull(entity);
        assertEquals(1L, entity.getPacienteId());
        assertEquals(2L, entity.getMedicoId());
        assertEquals(dataConsulta, entity.getDataConsulta());
        assertEquals("Checkup", entity.getMotivo());
    }

    @Test
    void deveConverterEntityParaDominio() {
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 4, 20, 14, 30);
        ConsultaEntity entity = new ConsultaEntity();
        entity.setId(1L);
        entity.setPacienteId(3L);
        entity.setMedicoId(4L);
        entity.setDataConsulta(dataConsulta);
        entity.setMotivo("Retorno");

        Consulta consulta = entity.toDomain();

        assertNotNull(consulta);
        assertEquals(1L, consulta.getId());
        assertEquals(3L, consulta.getPacienteId());
        assertEquals(4L, consulta.getMedicoId());
        assertEquals(dataConsulta, consulta.getDataConsulta());
        assertEquals("Retorno", consulta.getMotivo());
    }

    @Test
    void deveUsarConstrutorVazio() {
        ConsultaEntity entity = new ConsultaEntity();
        assertNotNull(entity);
        assertNull(entity.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 5, 10, 9, 0);
        PacienteEntity paciente = new PacienteEntity();
        MedicoEntity medico = new MedicoEntity();

        ConsultaEntity entity = new ConsultaEntity(
                1L, paciente, 2L, medico, 3L, dataConsulta, "Emergência");

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(2L, entity.getPacienteId());
        assertEquals(3L, entity.getMedicoId());
    }

    @Test
    void deveDefinirEObterPropriedades() {
        ConsultaEntity entity = new ConsultaEntity();
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 6, 1, 15, 0);
        PacienteEntity paciente = new PacienteEntity();
        MedicoEntity medico = new MedicoEntity();

        entity.setId(1L);
        entity.setPaciente(paciente);
        entity.setPacienteId(5L);
        entity.setMedico(medico);
        entity.setMedicoId(6L);
        entity.setDataConsulta(dataConsulta);
        entity.setMotivo("Consulta de rotina");

        assertEquals(1L, entity.getId());
        assertNotNull(entity.getPaciente());
        assertEquals(5L, entity.getPacienteId());
        assertNotNull(entity.getMedico());
        assertEquals(6L, entity.getMedicoId());
    }
}
