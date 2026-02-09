package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AvaliacaoEntityTest {

    @Test
    void deveCriarEntityAPartirDeDominio() {
        Avaliacao avaliacao = new Avaliacao(null, 1L, 5, "Excelente");
        AvaliacaoEntity entity = new AvaliacaoEntity(avaliacao);

        assertNotNull(entity);
        assertEquals(1L, entity.getConsultaId());
        assertEquals(5, entity.getNota());
        assertEquals("Excelente", entity.getComentario());
    }

    @Test
    void deveConverterEntityParaDominio() {
        AvaliacaoEntity entity = new AvaliacaoEntity();
        entity.setId(1L);
        entity.setConsultaId(2L);
        entity.setNota(4);
        entity.setComentario("Bom");

        Avaliacao avaliacao = entity.toDomain();

        assertNotNull(avaliacao);
        assertEquals(1L, avaliacao.getId());
        assertEquals(2L, avaliacao.getConsultaId());
        assertEquals(4, avaliacao.getNota());
        assertEquals("Bom", avaliacao.getComentario());
    }

    @Test
    void deveUsarConstrutorVazio() {
        AvaliacaoEntity entity = new AvaliacaoEntity();
        assertNotNull(entity);
        assertNull(entity.getId());
    }

    @Test
    void deveUsarConstrutorCompleto() {
        ConsultaEntity consulta = new ConsultaEntity();
        AvaliacaoEntity entity = new AvaliacaoEntity(1L, consulta, 2L, 5, "Ótimo");

        assertNotNull(entity);
        assertEquals(1L, entity.getId());
        assertEquals(2L, entity.getConsultaId());
        assertEquals(5, entity.getNota());
        assertEquals("Ótimo", entity.getComentario());
    }

    @Test
    void deveDefinirEObterPropriedades() {
        AvaliacaoEntity entity = new AvaliacaoEntity();
        ConsultaEntity consulta = new ConsultaEntity();

        entity.setId(1L);
        entity.setConsulta(consulta);
        entity.setConsultaId(3L);
        entity.setNota(3);
        entity.setComentario("Regular");

        assertEquals(1L, entity.getId());
        assertNotNull(entity.getConsulta());
        assertEquals(3L, entity.getConsultaId());
        assertEquals(3, entity.getNota());
        assertEquals("Regular", entity.getComentario());
    }
}
