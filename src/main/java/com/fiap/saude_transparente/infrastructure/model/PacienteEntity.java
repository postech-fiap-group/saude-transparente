package com.fiap.saude_transparente.infrastructure.model;

import com.fiap.saude_transparente.domain.entities.Paciente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name="paciente")
@AllArgsConstructor
@NoArgsConstructor
public class PacienteEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sobrenome;
	private String telefone;
	private String email;
	private String cpf;
	private String endereco;
	private LocalDate dataNascimento;

	public PacienteEntity(Paciente paciente){
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.sobrenome = paciente.getSobrenome();
		this.telefone = paciente.getTelefone();
		this.email = paciente.getEmail();
		this.cpf = paciente.getCpf();
		this.endereco = paciente.getEndereco();
		this.dataNascimento = paciente.getDataNascimento();
	}

	public Paciente toDomain(){
		var paciente = Paciente.criar(
				this.nome,
				this.sobrenome,
				this.telefone,
				this.email,
				this.cpf,
				this.endereco,
				this.dataNascimento
		);
		paciente.setId(this.id);
		return paciente;
	}
}
