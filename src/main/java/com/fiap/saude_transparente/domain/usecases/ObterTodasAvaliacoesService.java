package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObterTodasAvaliacoesService {

	private final AvaliacaoGateway avaliacaoGateway;

	public List<Avaliacao> getAll(int page, int size){
		int offset = (page-1) * size;

		return this.avaliacaoGateway.getAll(size,offset);
	}
}
