package com.fiap.saude_transparente.domain.commands;

import java.time.LocalDate;

public record CriarPacienteCommand(
        String nome,
        String sobrenome,
        String telefone,
        String email,
        String cpf,
        String endereco,
        LocalDate dataNascimento
) {}