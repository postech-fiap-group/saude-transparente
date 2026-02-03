package com.fiap.saude_transparente.application.presenter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstatisticaEspecialidadeDTO {
	private String especialidade;
	private Double mediaNotas;
	private Long totalAvaliacoes;
	private Integer notaMinima;
	private Integer notaMaxima;
}