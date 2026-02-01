package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObterComentariosPorMedicoId {

	private final AvaliacaoGateway avaliacaoGateway;

	public List<String> execute(Long medicoId){
		return this.avaliacaoGateway
				.getAllComentariosByMedicoId(medicoId);

	}
}
