package com.fiap.saude_transparente.domain.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoTest {

    @Test
    void deveCriarAvaliacao() {
        Avaliacao avaliacao = Avaliacao.criar(1L, 5, "Excelente atendimento");

        assertNotNull(avaliacao);
        assertEquals(1L, avaliacao.getConsultaId());
        assertEquals(5, avaliacao.getNota());
        assertEquals("Excelente atendimento", avaliacao.getComentario());
    }

    @Test
    void deveAlterarAvaliacao() {
        Avaliacao avaliacao = Avaliacao.alterar(1L, 2L, 4, "Bom atendimento");

        assertNotNull(avaliacao);
        assertEquals(1L, avaliacao.getId());
        assertEquals(2L, avaliacao.getConsultaId());
        assertEquals(4, avaliacao.getNota());
        assertEquals("Bom atendimento", avaliacao.getComentario());
    }

    @Test
    void deveUsarConstrutorVazio() {
        Avaliacao avaliacao = new Avaliacao();
        assertNotNull(avaliacao);
        assertNull(avaliacao.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        Avaliacao avaliacao = new Avaliacao(1L, 2L, 5, "Ótimo");

        assertNotNull(avaliacao);
        assertEquals(1L, avaliacao.getId());
        assertEquals(2L, avaliacao.getConsultaId());
        assertEquals(5, avaliacao.getNota());
        assertEquals("Ótimo", avaliacao.getComentario());
    }

    @Test
    void deveDefinirEObterPropriedades() {
        Avaliacao avaliacao = new Avaliacao();

        avaliacao.setId(1L);
        avaliacao.setConsultaId(3L);
        avaliacao.setNota(3);
        avaliacao.setComentario("Regular");

        assertEquals(1L, avaliacao.getId());
        assertEquals(3L, avaliacao.getConsultaId());
        assertEquals(3, avaliacao.getNota());
        assertEquals("Regular", avaliacao.getComentario());
    }
}
