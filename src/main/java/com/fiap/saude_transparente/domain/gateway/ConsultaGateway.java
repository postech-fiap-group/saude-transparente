package com.fiap.saude_transparente.domain.gateway;

import com.fiap.saude_transparente.domain.entities.Consulta;

import java.util.List;

public interface ConsultaGateway {

	List<Consulta> getAllConsultas(int size, int offset);
	Consulta getConsultaById(Long id);
	Long criarConsulta(Consulta consulta);
	Long alterarConsulta(Consulta consulta);
	void deletarConsulta(Long id);
}
