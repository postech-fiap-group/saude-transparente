package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.entities.Consulta;
import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ObterConsultasService {

    private final ConsultaGateway consultaGateway;

    public List<Consulta> execute(int size, int offset) {
        return consultaGateway.getAllConsultas(size, offset);
    }
}
