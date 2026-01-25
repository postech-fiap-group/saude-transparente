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
	private String sobreNome;
	private Especialidades especialidade;
	private String crm;
	private String telefone;
	private String email;
	private String cpf;
	private LocalDate dataNascimento;
	private String endereco;

	public static Medico criar(String nome, String sobreNome, Especialidades especialidade, String crm, String telefone, String email, String cpf,String endereco, LocalDate dataNascimento) {

		var medico = new Medico();
		medico.setNome(nome);
		medico.setSobreNome(sobreNome);
		medico.setEspecialidade(especialidade);
		medico.setCrm(crm);
		medico.setTelefone(telefone);
		medico.setEmail(email);
		medico.setCpf(cpf);
		medico.setEndereco(endereco);
		medico.setDataNascimento(dataNascimento);
		return medico;

	}

	public static Medico alterar(Long id, String nome, String sobreNome, Especialidades especialidade, String crm, String telefone, String email,  String cpf,String endereco, LocalDate dataNascimento) {

		var medico = new Medico();
		medico.setId(id);
		medico.setNome(nome);
		medico.setSobreNome(sobreNome);
		medico.setEspecialidade(especialidade);
		medico.setCrm(crm);
		medico.setTelefone(telefone);
		medico.setEmail(email);
		medico.setCpf(cpf);
		medico.setEndereco(endereco);
		medico.setDataNascimento(dataNascimento);

		return medico;
	}
}
