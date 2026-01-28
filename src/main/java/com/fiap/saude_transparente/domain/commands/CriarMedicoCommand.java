package com.fiap.saude_transparente.domain.commands;

import com.fiap.saude_transparente.domain.enums.Especialidades;

import java.time.LocalDate;

public record CriarMedicoCommand(
        String nome,
        String sobrenome,
        String cpf,
        String crm,
        Especialidades especialidade,
        String endereco,
        String telefone,
        String email,
        LocalDate dataNascimento) {

}
