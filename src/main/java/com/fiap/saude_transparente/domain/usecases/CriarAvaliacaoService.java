package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.CriarAvaliacaoCommand;
import com.fiap.saude_transparente.domain.entities.Avaliacao;
import com.fiap.saude_transparente.domain.gateway.AvaliacaoGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CriarAvaliacaoService {

	private final AvaliacaoGateway avaliacaoGateway;

	public void save(CriarAvaliacaoCommand cmd){
		var incluirAvaliacao = Avaliacao.criar(cmd.consultaId(), cmd.nota(), cmd.comentario());

		var save = this.avaliacaoGateway.criarAvaliacao(incluirAvaliacao);


		if (save != 1) {
			throw new RuntimeException("Erro ao salvar usuário");
		}
	}
}
