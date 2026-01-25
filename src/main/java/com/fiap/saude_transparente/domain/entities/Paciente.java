package com.fiap.saude_transparente.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {

	private Long id;
	private String nome;
	private String sobreNome;
	private String telefone;
	private String email;
	private String cpf;
	private String endereco;
	private LocalDate dataNascimento;

	public static Paciente criar(String nome, String sobreNome, String telefone, String email, String cpf,String endereco,LocalDate dataNascimento) {

		var paciente = new Paciente();
		paciente.setNome(nome);
		paciente.setSobreNome(sobreNome);
		paciente.setTelefone(telefone);
		paciente.setEmail(email);
		paciente.setCpf(cpf);
		paciente.setEndereco(endereco);
		paciente.setDataNascimento(dataNascimento);

		return paciente;

	}

	public static Paciente alterar(Long id, String nome, String sobreNome, String telefone, String email, String cpf,String endereco, LocalDate dataNascimento) {

		var paciente = new Paciente();
		paciente.setId(id);
		paciente.setNome(nome);
		paciente.setSobreNome(sobreNome);
		paciente.setTelefone(telefone);
		paciente.setEmail(email);
		paciente.setCpf(cpf);
		paciente.setEndereco(endereco);
		paciente.setDataNascimento(dataNascimento);

		return paciente;
	}
}
