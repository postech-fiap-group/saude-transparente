package com.fiap.saude_transparente.application.presenter;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class NotaResponse {
	private Long medicoId;
	private String medicoNome;
	private String especialidade;
	private Double mediaNotas;
	private int totalAvaliacoes;
	private Double notaMaxima;
	private Double notaMinima;

}
