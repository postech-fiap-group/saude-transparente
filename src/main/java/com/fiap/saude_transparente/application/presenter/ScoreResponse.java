package com.fiap.saude_transparente.application.presenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ScoreResponse {
	private Long medicoId;
	private String medicoNome;
	private String especialidade;
	private String crm;
	private NotaResponse score;
}
