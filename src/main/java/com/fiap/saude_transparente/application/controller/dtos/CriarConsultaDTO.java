package com.fiap.saude_transparente.application.controller.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record CriarConsultaDTO(
    @NotNull(message = "Paciente ID não pode ser nulo")
    Long pacienteId,
    @NotNull(message = "Medico ID não pode ser nulo")
    Long medicoId,
    @NotNull(message = "Data da Consulta não pode ser nula")
    @Future(message = "A data da consulta tem que ser futura")
    LocalDateTime dataConsulta,
    @NotBlank(message = "O motivo não pode ser vazio")
    String motivo
) {
}
