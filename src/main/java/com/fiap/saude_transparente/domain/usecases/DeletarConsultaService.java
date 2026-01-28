package com.fiap.saude_transparente.domain.usecases;

import com.fiap.saude_transparente.domain.gateway.ConsultaGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeletarConsultaService {

    private final ConsultaGateway consultaGateway;

    public void execute(Long id) {
        this.consultaGateway.deletarConsulta(id);
    }
}
