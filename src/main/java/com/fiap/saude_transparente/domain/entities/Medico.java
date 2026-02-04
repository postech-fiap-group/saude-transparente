package com.fiap.saude_transparente.domain.entities;

import com.fiap.saude_transparente.domain.enums.Especialidades;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Medico {

	private Long id;
	private String nome;
	private String sobrenome;
	private String especialidade;
	private String crm;
	private String endereco;
	private String email;
	private String telefone;
	private String cpf;
	private LocalDate dataNascimento;

	public static Medico criar(String nome,
							   String sobrenome,
							   String especialidade,
							   String crm,
							   String endereco,
							   String email,
							   String telefone,
							   String cpf,
							   LocalDate dataNascimento) {

		var medico = new Medico();
		medico.setNome(nome);
		medico.setSobrenome(sobrenome);
		medico.setEspecialidade(especialidade);
		medico.setCrm(crm);
		medico.setEndereco(endereco);
		medico.setEmail(email);
		medico.setTelefone(telefone);
		medico.setCpf(cpf);
		medico.setDataNascimento(dataNascimento);
		return medico;

	}

	public static Medico alterar(Long id,
								 String nome,
								 String sobrenome,
								 String especialidade,
								 String crm,
								 String endereco,
								 String email,
								 String telefone,
								 String cpf,
								 LocalDate dataNascimento) {

		var medico = new Medico();
		medico.setId(id);
		medico.setNome(nome);
		medico.setSobrenome(sobrenome);
		medico.setEspecialidade(especialidade);
		medico.setCrm(crm);
		medico.setEndereco(endereco);
		medico.setEmail(email);
		medico.setTelefone(telefone);
		medico.setCpf(cpf);
		medico.setDataNascimento(dataNascimento);

		return medico;
	}
}
