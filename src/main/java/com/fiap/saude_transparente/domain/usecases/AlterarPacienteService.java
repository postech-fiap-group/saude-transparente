package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.commands.AlterarPacienteCommand;
import com.fiap.saude_transparente.domain.entities.Paciente;
import com.fiap.saude_transparente.domain.exceptions.PacienteNaoOuNaoPodeCriarException;
import com.fiap.saude_transparente.domain.gateway.PacienteGateway;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AlterarPacienteService {

    private final PacienteGateway pacienteGateway;

    public void save(AlterarPacienteCommand cmd) {

        var id = this.pacienteGateway.alterarPaciente(
                Paciente.alterar(
                        cmd.id(),
                        cmd.nome(),
                        cmd.sobrenome(),
                        cmd.telefone(),
                        cmd.email(),
                        cmd.cpf(),
                        cmd.endereco(),
                        cmd.dataNascimento()
                )
        );

        if (id == null || id <= 0) {
            throw new PacienteNaoOuNaoPodeCriarException("Erro ao alterar o paciente. Verifique os dados e tente novamente!");
        }
    }
}
