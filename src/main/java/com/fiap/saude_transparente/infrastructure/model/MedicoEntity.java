package com.fiap.saude_transparente.infrastructure.model;


import com.fiap.saude_transparente.domain.entities.Medico;
import com.fiap.saude_transparente.domain.enums.Especialidades;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="medico")
@AllArgsConstructor
public class MedicoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String sobreNome;
	private Especialidades especialidade;
	private String crm;
	private String telefone;
	private String email;
	private String cpf;
	private String endereco;
	private LocalDate dataNascimento;

	public MedicoEntity(Medico medico){
		this.id = medico.getId();
		this.nome = medico.getNome();
		this.sobreNome = medico.getSobreNome();
		this.especialidade = medico.getEspecialidade();
		this.crm = medico.getCrm();
		this.telefone = medico.getTelefone();
		this.email = medico.getEmail();
		this.cpf = medico.getCpf();
		this.endereco = medico.getEndereco();
		this.dataNascimento = medico.getDataNascimento();
	}

	public Medico toDomain(){
		var medico = Medico.criar(
				this.nome,
				this.sobreNome,
				this.especialidade,
				this.crm,
				this.telefone,
				this.email,
				this.cpf,
				this.endereco,
				this.dataNascimento
		);
		medico.setId(this.id);
		return medico;
	}
}
