package com.fiap.saude_transparente.domain.gateway;

import com.fiap.saude_transparente.domain.entities.Paciente;

import java.util.List;

public interface PacienteGateway {

	Paciente getPacienteById(Long id);
	List<Paciente> getAllPacientes(int size, int offset);
	Paciente criarPaciente(Paciente paciente);
	Paciente atualizarPaciente(Paciente paciente);
	void deletarPaciente(Long id);

}
