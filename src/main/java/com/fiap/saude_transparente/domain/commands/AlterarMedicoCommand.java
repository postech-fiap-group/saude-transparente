package com.fiap.saude_transparente.domain.commands;

import java.time.LocalDate;

public record AlterarMedicoCommand(Long id,
                                   String nome,
                                   String sobrenome,
                                   String especialidade,
                                   String crm,
                                   String endereco,
                                   String email,
                                   String telefone,
                                   String cpf,
                                   LocalDate dataNascimento) {
}
