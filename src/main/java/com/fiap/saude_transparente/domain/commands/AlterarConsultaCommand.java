package com.fiap.saude_transparente.domain.commands;

import java.time.LocalDateTime;

public record AlterarConsultaCommand(
        Long id,
        Long pacienteId,
        Long medicoId,
        LocalDateTime dataConsulta,
        String motivo
) {
}
