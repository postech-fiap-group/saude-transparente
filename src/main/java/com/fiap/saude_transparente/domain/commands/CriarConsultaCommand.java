package com.fiap.saude_transparente.domain.commands;

import java.time.LocalDateTime;

public record CriarConsultaCommand(
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataConsulta,
        String motivo
) {
}
