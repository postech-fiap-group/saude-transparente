package com.fiap.saude_transparente.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Avaliacao {

	private Long id;
	private Long consultaId;
	private int nota;
	private String comentario;

	public static Avaliacao criar(Long consultaId, int nota, String comentario) {

		var avaliacao = new Avaliacao();
		avaliacao.setId(Long.parseLong(UUID.randomUUID().toString()));
		avaliacao.setConsultaId(consultaId);
		avaliacao.setNota(nota);
		avaliacao.setComentario(comentario);

		return avaliacao;

	}

	public static Avaliacao alterar(Long id,Long consultaId, int setConsultaId, String comentario) {

		var avaliacao = new Avaliacao();
		avaliacao.setId(id);
		avaliacao.setConsultaId(consultaId);
		avaliacao.setNota(setConsultaId);
		avaliacao.setComentario(comentario);

		return avaliacao;
	}
}
