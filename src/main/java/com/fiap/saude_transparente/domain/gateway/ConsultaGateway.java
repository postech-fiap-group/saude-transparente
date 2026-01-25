package com.fiap.saude_transparente.domain.gateway;

import com.fiap.saude_transparente.domain.entities.Consulta;

import java.util.List;

public interface ConsultaGateway {

	Consulta getConsultaById(Long id);
	List<Consulta> getAllConsultas(int size, int offset);
	Consulta criarConsulta(Consulta consulta);
	Consulta atualizarConsulta(Consulta consulta);
	void deletarConsulta(Long id);
}
