package com.fiap.saude_transparente.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

	private Long id;
	private Long pacienteId;
	private Long medicoId;
	private LocalDateTime dataConsulta;
	private String motivo;

	public static Consulta criar(Long pacienteId, Long medicoId, LocalDateTime dataConsulta, String motivo) {

		var consulta = new Consulta();
		consulta.setPacienteId(pacienteId);
		consulta.setMedicoId(medicoId);
		consulta.setDataConsulta(dataConsulta);
		consulta.setMotivo(motivo);

		return consulta;

	}

	public static Consulta alterar(Long id, Long pacienteId, Long medicoId, LocalDateTime dataConsulta, String motivo) {

		var consulta = new Consulta();
		consulta.setId(id);
		consulta.setPacienteId(pacienteId);
		consulta.setMedicoId(medicoId);
		consulta.setDataConsulta(dataConsulta);
		consulta.setMotivo(motivo);

		return consulta;
	}

}
