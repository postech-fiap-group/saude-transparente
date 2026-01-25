package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Consulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="consulta")
@AllArgsConstructor
public class ConsultaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long pacienteId;
	private Long medicoId;
	private LocalDateTime dataConsulta;
	private String motivo;

	public ConsultaEntity(Consulta consulta){
		this.id = consulta.getId();
		this.pacienteId = consulta.getPacienteId();
		this.medicoId = consulta.getMedicoId();
		this.dataConsulta = consulta.getDataConsulta();
		this.motivo = consulta.getMotivo();
	}

	public Consulta toDomain(){
		var consulta = Consulta.criar(this.pacienteId, this.medicoId, this.dataConsulta, this.motivo);
		consulta.setId(this.id);
		return consulta;
	}

}
