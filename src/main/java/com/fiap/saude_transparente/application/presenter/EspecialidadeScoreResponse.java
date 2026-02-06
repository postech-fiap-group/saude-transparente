package com.fiap.saude_transparente.application.presenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EspecialidadeScoreResponse {
	private String especialidade;
	private NotaResponse score;
}
