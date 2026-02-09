package com.fiap.saude_transparente.domain.gateway;

import com.fiap.saude_transparente.domain.entities.Paciente;

import java.util.List;

public interface PacienteGateway {

	List<Paciente> getAllPacientes(int page, int size);
	Paciente getPacienteById(Long id);
	Long criarPaciente(Paciente paciente);
	boolean existePacienteById(Long id);
	Long alterarPaciente(Paciente paciente);
	void deletarPaciente(Long id);
}
