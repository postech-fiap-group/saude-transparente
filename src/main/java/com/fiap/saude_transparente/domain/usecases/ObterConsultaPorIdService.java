package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ObterConsultaPorIdService {

    private final ConsultaGateway consultaGateway;

    public Consulta execute(Long id) {
        return this.consultaGateway.getConsultaById(id);
    }
}
