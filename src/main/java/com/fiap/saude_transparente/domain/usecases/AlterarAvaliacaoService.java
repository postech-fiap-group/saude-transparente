package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.AlterarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AlterarAvaliacaoService {

	private final AvaliacaoGateway avaliacaoGateway;

	public void save(AlterarAvaliacaoCommand cmd){

		var id = this.avaliacaoGateway.alterarAvaliacao(Avaliacao.alterar(cmd.id(),cmd.consultaId(), cmd.nota(), cmd.comentario()));

		if (id == null || id <= 0) {
			throw new RuntimeException("Erro ao alterar usuário");
		}
	}
}
