package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Consulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name="consulta")
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "paciente_id", referencedColumnName = "id")
	private PacienteEntity paciente;
	@Column(name="paciente_id", insertable = false, updatable = false)
	private Long pacienteId;
	@ManyToOne
	@JoinColumn(name = "medico_id", referencedColumnName = "id")
	private MedicoEntity medico;
	@Column(name="medico_id", insertable = false, updatable = false)
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
