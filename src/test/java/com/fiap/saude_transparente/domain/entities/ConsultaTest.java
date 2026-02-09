package com.fiap.saude_transparente.domain.entities;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ConsultaTest {

    @Test
    void deveCriarConsulta() {
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 3, 15, 10, 0);
        Consulta consulta = Consulta.criar(1L, 2L, dataConsulta, "Checkup anual");

        assertNotNull(consulta);
        assertEquals(1L, consulta.getPacienteId());
        assertEquals(2L, consulta.getMedicoId());
        assertEquals(dataConsulta, consulta.getDataConsulta());
        assertEquals("Checkup anual", consulta.getMotivo());
    }

    @Test
    void deveAlterarConsulta() {
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 4, 20, 14, 30);
        Consulta consulta = Consulta.alterar(1L, 3L, 4L, dataConsulta, "Retorno");

        assertNotNull(consulta);
        assertEquals(1L, consulta.getId());
        assertEquals(3L, consulta.getPacienteId());
        assertEquals(4L, consulta.getMedicoId());
        assertEquals(dataConsulta, consulta.getDataConsulta());
        assertEquals("Retorno", consulta.getMotivo());
    }

    @Test
    void deveUsarConstrutorVazio() {
        Consulta consulta = new Consulta();
        assertNotNull(consulta);
        assertNull(consulta.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 5, 10, 9, 0);
        Consulta consulta = new Consulta(1L, 2L, 3L, dataConsulta, "Emergência");

        assertNotNull(consulta);
        assertEquals(1L, consulta.getId());
        assertEquals(2L, consulta.getPacienteId());
        assertEquals(3L, consulta.getMedicoId());
    }

    @Test
    void deveDefinirEObterPropriedades() {
        Consulta consulta = new Consulta();
        LocalDateTime dataConsulta = LocalDateTime.of(2026, 6, 1, 15, 0);

        consulta.setId(1L);
        consulta.setPacienteId(5L);
        consulta.setMedicoId(6L);
        consulta.setDataConsulta(dataConsulta);
        consulta.setMotivo("Consulta de rotina");

        assertEquals(1L, consulta.getId());
        assertEquals(5L, consulta.getPacienteId());
        assertEquals(6L, consulta.getMedicoId());
        assertEquals(dataConsulta, consulta.getDataConsulta());
        assertEquals("Consulta de rotina", consulta.getMotivo());
    }
}
