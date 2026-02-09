package com.fiap.saude_transparente.domain.gateway;

import com.fiap.saude_transparente.domain.entities.Medico;

import java.util.List;

public interface MedicoGateway {

	Medico getMedicoById(Long id);
	List<Medico> getAllMedicos(int size, int offset);
	Medico criarMedico(Medico medico);
	boolean existeMedicoById(Long id);
	void atualizarMedico(Medico medico);
	void deletarMedico(Long id);

}
